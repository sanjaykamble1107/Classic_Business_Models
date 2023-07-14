package com.sprint.classicmodelsbussiness.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handler(EmployeeNotFoundException e) {
		ExceptionResponse exception = new ExceptionResponse(e.getMessage(), LocalDateTime.now(),
				HttpStatus.NOT_FOUND.value());
		// new
		// ExceptionResponse(e.getMessage(),LocalDateTime.now(),HttpStatus.NOT_FOUND.value());

		ResponseEntity<ExceptionResponse> responseEntity = new ResponseEntity<ExceptionResponse>(exception,
				HttpStatus.NOT_FOUND);
		return responseEntity;

	}

	@ExceptionHandler(OfficeNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handler(OfficeNotFoundException e) {
		ExceptionResponse exception = new ExceptionResponse(e.getMessage(), LocalDateTime.now(),
				HttpStatus.NOT_FOUND.value());
		// new
		// ExceptionResponse(e.getMessage(),LocalDateTime.now(),HttpStatus.NOT_FOUND.value());

		ResponseEntity<ExceptionResponse> responseEntity = new ResponseEntity<ExceptionResponse>(exception,
				HttpStatus.NOT_FOUND);
		return responseEntity;

	}

	@ExceptionHandler(CustomersNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handler(CustomersNotFoundException e) {
		ExceptionResponse exception = new ExceptionResponse(e.getMessage(), LocalDateTime.now(),
				HttpStatus.NOT_FOUND.value());
		ResponseEntity<ExceptionResponse> responseEntity = new ResponseEntity<ExceptionResponse>(exception,
				HttpStatus.NOT_FOUND);
		return responseEntity;

	}

	@ExceptionHandler(ProductNotFoundException.class)

	public ResponseEntity<ExceptionResponse> handler(ProductNotFoundException e)

	{

		ExceptionResponse exception = new ExceptionResponse(e.getMessage(),

				LocalDateTime.now(), HttpStatus.NOT_FOUND.value());

		ResponseEntity<ExceptionResponse> responseEntity = new ResponseEntity<ExceptionResponse>(exception,
				HttpStatus.NOT_FOUND);

		return responseEntity;

	}

	@ExceptionHandler(ProductLinesNotFoundException.class)

	public ResponseEntity<ExceptionResponse> handler(ProductLinesNotFoundException e)

	{

		ExceptionResponse exception = new ExceptionResponse(e.getMessage(),

				LocalDateTime.now(), HttpStatus.NOT_FOUND.value());

		ResponseEntity<ExceptionResponse> responseEntity = new ResponseEntity<ExceptionResponse>(exception,
				HttpStatus.NOT_FOUND);

		return responseEntity;

	}

	@ExceptionHandler(InsufficientStockException.class)

	public ResponseEntity<ExceptionResponse> handler(InsufficientStockException e)

	{

		ExceptionResponse exception = new ExceptionResponse(e.getMessage(),

				LocalDateTime.now(), HttpStatus.NOT_FOUND.value());

		ResponseEntity<ExceptionResponse> responseEntity = new ResponseEntity<ExceptionResponse>(exception,
				HttpStatus.NOT_FOUND);

		return responseEntity;

	}

	@ExceptionHandler(OrderDetailsNotFoundException.class)

	public ResponseEntity<ExceptionResponse> handler(OrderDetailsNotFoundException e)

	{

		ExceptionResponse exception = new ExceptionResponse(e.getMessage(),

				LocalDateTime.now(), HttpStatus.NOT_FOUND.value());

		ResponseEntity<ExceptionResponse> responseEntity = new ResponseEntity<ExceptionResponse>(exception,
				HttpStatus.NOT_FOUND);

		return responseEntity;

	}

	@ExceptionHandler(OrderNotFoundException.class)

	public ResponseEntity<ExceptionResponse> handler(OrderNotFoundException e)

	{

		ExceptionResponse exception = new ExceptionResponse(e.getMessage(),

				LocalDateTime.now(), HttpStatus.NOT_FOUND.value());

		ResponseEntity<ExceptionResponse> responseEntity = new ResponseEntity<ExceptionResponse>(exception,
				HttpStatus.NOT_FOUND);

		return responseEntity;

	}

	@ExceptionHandler(ConstraintViolationException.class)

	public ResponseEntity<?> handleConstraintViolationExc(ConstraintViolationException ex, WebRequest request) {

		ExceptionResponse errRes = new ExceptionResponse(ex.getMessage(), LocalDateTime.now(),

				HttpStatus.NOT_FOUND.value());

		System.out.println("it is not detected");

		return new ResponseEntity<>(errRes, HttpStatus.BAD_REQUEST);

	}

	@Override

	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,

			org.springframework.http.HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<String> validationErrs = new ArrayList<>();

		for (FieldError err : ex.getBindingResult().getFieldErrors()) {

			System.out.println("---->" + err.getDefaultMessage());

			validationErrs.add(err.getDefaultMessage());

		}

		ErrorResponse errResp = new ErrorResponse("Validation Failed", validationErrs.toString());

		return new ResponseEntity<Object>(errResp, status);

	}
}