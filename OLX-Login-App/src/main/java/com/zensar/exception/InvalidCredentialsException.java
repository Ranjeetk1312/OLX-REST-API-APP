package com.zensar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidCredentialsException extends RuntimeException{
	private String message;

	public InvalidCredentialsException(String message) {
		this.message = message;
	}

	public InvalidCredentialsException() {
		this.message="";
	}

	@Override
	public String toString() {
		return "InvalidCredentialsException [message=" + message + "]";
	}
	
}
