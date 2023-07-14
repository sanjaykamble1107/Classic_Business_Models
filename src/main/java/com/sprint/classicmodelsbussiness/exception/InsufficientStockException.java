package com.sprint.classicmodelsbussiness.exception;

public class InsufficientStockException extends RuntimeException {

	private static final long serialVersionUID = 4230426823309653405L;

	public InsufficientStockException() {
		super();

	}

	public InsufficientStockException(String message) {
		super(message);

	}
}
