package com.olxlogin.service;


import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.zensar.exception.InvalidAdvertiseIdException;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service

public class LoginServiceDelegateImpl implements LoginServiceDelegate {

	@Autowired
	RestTemplate restTemplate;
	
	@CircuitBreaker(name="AUTH_TOKEN_VALIDATION", fallbackMethod="fallbackIsTokenValid")
	@Override
	public boolean isTokenValid(String authToken) {
	HttpHeaders headers = new HttpHeaders();
	headers.setContentType(MediaType.APPLICATION_JSON);
	headers.set("Authorization", authToken);
	HttpEntity entity = new HttpEntity(headers);
	ResponseEntity<Boolean> response =
	//this.restTemplate.exchange("http://localhost:9001/olx/login/token/validate", HttpMethod.GET, entity, Boolean.class);
			//this.restTemplate.exchange("http://AUTH-SERVICE/olx/login/token/validate", HttpMethod.GET, entity, Boolean.class);
			this.restTemplate.exchange("http://API-GATEWAY/olx/login/token/validate", HttpMethod.GET, entity, Boolean.class);
	return response.getBody();
	}
	
	public boolean fallbackIsTokenValid(String authToken,Exception exception) {
		System.out.println("Olx Login failed Inside fallbackIsTokenValid" + exception);
		return false;
	  }
	 

	@Override
	public String isIdPresent(int categoryId) {
		
		
		return null;
	}

	@Override
	public String returnUserName(String authToken) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", authToken);
		HttpEntity entity = new HttpEntity(headers);
		ResponseEntity<String> response =
				//this.restTemplate.exchange("http://localhost:9001/olx/login/user/username", HttpMethod.GET, entity, String.class);
				//this.restTemplate.exchange("http://AUTH-SERVICE/olx/login/user/username", HttpMethod.GET, entity, String.class);
				this.restTemplate.exchange("http://API-GATEWAY/olx/login/user/username", HttpMethod.GET, entity, String.class);
		return response.getBody();
	}

	@Override
	public String getCategoryFromMastedata(int categoryId) {
		HashMap<String, Integer> params = new HashMap<>();
		params.put("userId",categoryId);
		try {
		    ResponseEntity<String> response
		        //= this.restTemplate.getForEntity("http://localhost:9002/olx/masterdata/advertise/category/{userId}",String.class, params);
	        //= this.restTemplate.getForEntity("http://MASTERDATA-SERVICE/olx/masterdata/advertise/category/{userId}",String.class, params);
		    = this.restTemplate.getForEntity("http://API-GATEWAY/olx/masterdata/advertise/category/{userId}",String.class, params);
		    return response.getBody();
		}
		catch (Exception ex) {
		    throw new InvalidAdvertiseIdException("Category Id " +categoryId+ " Not Found");
		}
	}

	@Override
	public String getStatusFromMastedata(int statusId) {
		HashMap<String, Integer> params = new HashMap<>();
		params.put("userId",statusId);
		try {
		    ResponseEntity<String> response
		    //= restTemplate.getForEntity("http://localhost:9002/olx/masterdata/advertise/status/{userId}",String.class, params);
		    //= restTemplate.getForEntity("http://MASTERDATA-SERVICE/olx/masterdata/advertise/status/{userId}",String.class, params);
		    = restTemplate.getForEntity("http://API-GATEWAY/olx/masterdata/advertise/status/{userId}",String.class, params);
		    return response.getBody();
		}
		catch (Exception ex) {
		    throw new InvalidAdvertiseIdException("Category Id " +statusId+ " Not Found");
		}
	}

}
