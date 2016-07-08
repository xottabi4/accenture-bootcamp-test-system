package com.accenture.abts.spring.exceptions;

public class IncorectTestTypeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3960461638225414124L;

	private String message = null;

	public IncorectTestTypeException(String message) {
		super(message);
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
