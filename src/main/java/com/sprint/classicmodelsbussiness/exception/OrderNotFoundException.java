package com.sprint.classicmodelsbussiness.exception;

public class OrderNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4230426823309653405L;

	public OrderNotFoundException() {
		super();

	}

	public OrderNotFoundException(String message) {
		super(message);

	}
}