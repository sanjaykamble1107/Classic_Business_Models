package com.sprint.classicmodelsbussiness.exception;

public class ProductLinesNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4230426823309653405L;

	public ProductLinesNotFoundException() {
		super();

	}

	public ProductLinesNotFoundException(String message) {
		super(message);
	}
}
