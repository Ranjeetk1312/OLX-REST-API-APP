package com.zensar.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(value=InvalidAdvertiseIdException.class)
	public ResponseEntity<Object> handlerConflict(RuntimeException exception, WebRequest request){
		String errorMessage = "{\"error\":\"Invalid InvalidAdvertiseIdException\"}";
		ResponseEntity<Object> response =
				handleExceptionInternal(exception,errorMessage,new HttpHeaders(),HttpStatus.CONFLICT,request);
		return response;
	}
	@ExceptionHandler(value=InvalidAuthTokenException.class)
	public ResponseEntity<Object> handlerConflict2(RuntimeException exception, WebRequest request){
		String errorMessage = "{\"error\":\"Invalid InvalidAuthTokenException\"}";
		ResponseEntity<Object> response =
				handleExceptionInternal(exception,errorMessage,new HttpHeaders(),HttpStatus.CONFLICT,request);
		return response;
	}
	
}
