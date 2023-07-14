package com.sprint.classicmodelsbussiness.exception;

public class OfficeNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4230426823309653405L;

	public OfficeNotFoundException() {
		super();

	}

	public OfficeNotFoundException(String message) {
		super(message);

	}
}
