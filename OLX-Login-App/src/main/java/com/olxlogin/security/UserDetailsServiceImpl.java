package com.olxlogin.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.olxlogin.entity.LoginEntity;
import com.olxlogin.repository.LoginRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	LoginRepo loginRepo;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	//write logic using Repository API to read information about the given username
	List<LoginEntity> userEntityList=loginRepo.findByUserName(username);
	
	if(userEntityList==null || userEntityList.size()==0) {
		throw new UsernameNotFoundException(username);
	}
	LoginEntity userEntity =userEntityList.get(0);
	List<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
	
	UserDetails userDetails =new User(username,this.passwordEncoder.encode(userEntity.getPassword()),authorities);
	return userDetails;
	}



}