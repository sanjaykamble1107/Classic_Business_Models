package com.sprint.classicmodelsbussiness.exception;

public class ProductNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4230426823309653405L;

	public ProductNotFoundException() {
		super();

	}

	public ProductNotFoundException(String message) {
		super(message);

	}
}