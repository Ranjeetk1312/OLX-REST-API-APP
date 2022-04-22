package com.olxlogin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olxlogin.dto.User;
import com.olxlogin.service.LoginService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/olx/login")
@CrossOrigin(origins="*")

public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	
	@PostMapping(value="/user/authonticate", consumes=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Login Authontication",notes="This REST API Login Authontication")
	public String authenticate(@RequestBody User user) {
		return loginService.authenticate(user);
	}
	@DeleteMapping(value="/user/logout")
	@ApiOperation(value="Logout User",notes="This REST API Logout User")
	public boolean logout(@RequestHeader("auth-token")String authToken) {
		return loginService.logout(authToken);
	}
	@PostMapping(value="/user", consumes= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value="Registration User",notes="This REST API Registration User")
	public User registers(@RequestBody User user) {
		return loginService.registers(user);
	}
	@GetMapping(value="/user",consumes= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value="User Information",notes="This REST API Return User Information")
	public List<User> returnUserInfo(@RequestHeader("Authorization")String authToken) {
		return loginService.returnUserInfo(authToken);
	}
	@GetMapping(value="/token/validate",consumes= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value="Validate Token",notes="This REST API Validate Token")
	public ResponseEntity<Boolean> validateToken(@RequestHeader("Authorization")String authToken){//(@RequestHeader("auth-token")
		return new ResponseEntity<Boolean>(loginService.validateToken(authToken), HttpStatus.OK);
	}

}
