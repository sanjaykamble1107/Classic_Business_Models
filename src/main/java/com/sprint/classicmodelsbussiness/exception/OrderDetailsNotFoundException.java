package com.sprint.classicmodelsbussiness.exception;

public class OrderDetailsNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4230426823309653405L;

	public OrderDetailsNotFoundException() {
		super();

	}

	public OrderDetailsNotFoundException(String message) {
		super(message);

	}
}