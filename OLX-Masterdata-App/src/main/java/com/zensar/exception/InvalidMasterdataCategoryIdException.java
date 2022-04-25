package com.zensar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidMasterdataCategoryIdException extends RuntimeException{
	private String message;

	public InvalidMasterdataCategoryIdException(String message) {
		this.message = message;
	}

	public InvalidMasterdataCategoryIdException() {
		this.message="";
	}

	@Override
	public String toString() {
		return "InvalidStockIdException [message=" + message + "]";
	}
	
}
