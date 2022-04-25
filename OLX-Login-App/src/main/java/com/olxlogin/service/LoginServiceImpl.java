package com.olxlogin.service;

import java.util.ArrayList;
import java.util.List;


import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.olxlogin.dto.User;
import com.olxlogin.entity.LoginEntity;
import com.olxlogin.repository.LoginRepo;
import com.olxlogin.security.JwtUtil;
import com.zensar.exception.InvalidAuthTokenException;
import com.zensar.exception.InvalidCredentialsException;

@Service(value="RDBMS_SERVICE")
@Primary
public class LoginServiceImpl implements LoginService {
	@Autowired
	LoginRepo loginRepo;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JwtUtil jwtUtil;
	@Autowired
	UserDetailsService userDetailsService;
	
	@Override
	public String authenticate(User user) {
		try {
			this.authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword()));
		}
		catch(AuthenticationException ex) {
			throw new InvalidCredentialsException(ex.toString());
		}
		String jwt = jwtUtil.generateToken(user.getUserName());
		
		return jwt;
		
	}

	@Override
	public boolean logout(String authToken) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public User registers(User user) {
		LoginEntity loginEntity= convertDtoIntoEntity(user);
		loginEntity = loginRepo.save(loginEntity);
		return convertEntityIntoDto(loginEntity);
	}

	@Override
	public List<User> returnUserInfo(String authToken) {
		List<User> userDtoList= new ArrayList<User>();
		if(validateToken(authToken)) {
			String authTokenArray[]=authToken.split(" ");
			authToken=authTokenArray[1];
			String username =jwtUtil.extractUsername(authToken);
			List<LoginEntity> loginEntityList = loginRepo.findByUserName(username);
			for(LoginEntity loginEntity:loginEntityList) {
				User user=convertEntityIntoDto(loginEntity);
				userDtoList.add(user);	
			}
		}
		return userDtoList;
	}
	@Override
	public boolean validateToken(String authToken) {
		try {
			String authTokenArray[]=authToken.split(" ");
			authToken=authTokenArray[1];
			String username =jwtUtil.extractUsername(authToken);
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			return jwtUtil.validateToken(authToken, userDetails);
		}
		catch(Exception ex) {
			return false;
		}
	}


	
	private LoginEntity convertDtoIntoEntity(User user) {
		TypeMap<User,LoginEntity> typeMap = 
				modelMapper.typeMap(User.class,LoginEntity.class);
		typeMap.addMappings(mapper->{
			mapper.map(loginDto->loginDto.getUserName(), LoginEntity::setUserName);});
		LoginEntity loginEntity = modelMapper.map(user,LoginEntity.class);
		return loginEntity;
	}
	private User convertEntityIntoDto(LoginEntity loginEntity) {
		TypeMap<LoginEntity,User> typeMap = 
				modelMapper.typeMap(LoginEntity.class,User.class);
		typeMap.addMappings(mapper->{
			mapper.map(LoginEntity->LoginEntity.getUserName(), User::setUserName);});
		User user = modelMapper.map(loginEntity,User.class);
		return user;
	}

	@Override
	public String returnUserName(String authToken) {
		if(validateToken(authToken)) {
			String authTokenArray[]=authToken.split(" ");
			authToken=authTokenArray[1];
			String username =jwtUtil.extractUsername(authToken);
			return username;
		}
		return null;
	}

}