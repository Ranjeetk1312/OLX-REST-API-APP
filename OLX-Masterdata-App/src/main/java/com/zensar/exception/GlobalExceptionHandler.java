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
	@ExceptionHandler(value=InvalidMasterdataCategoryIdException.class)
	public ResponseEntity<Object> handlerConflict(RuntimeException exception, WebRequest request){
		String errorMessage = "{\"error\":\"Invalid stock id\"}";
		ResponseEntity<Object> response =
				handleExceptionInternal(exception,errorMessage,new HttpHeaders(),HttpStatus.CONFLICT,request);
		return response;
	}
}