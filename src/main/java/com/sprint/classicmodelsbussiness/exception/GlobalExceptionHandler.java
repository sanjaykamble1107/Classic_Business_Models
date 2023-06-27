package com.sprint.classicmodelsbussiness.exception;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	
	@ExceptionHandler(EmployeeNotFoundException.class)
 	public ResponseEntity<ExceptionResponse> handler(EmployeeNotFoundException e)
 	{
 		ExceptionResponse exception = new ExceptionResponse(e.getMessage(),
 				LocalDateTime.now(), HttpStatus.NOT_FOUND.value());
 				//new ExceptionResponse(e.getMessage(),LocalDateTime.now(),HttpStatus.NOT_FOUND.value());
 		
 		ResponseEntity<ExceptionResponse> responseEntity = new ResponseEntity<ExceptionResponse>(exception,HttpStatus.NOT_FOUND);
 		return responseEntity;
 		
 	}

}
