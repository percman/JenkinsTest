package com.revature.exception;

public class InvalidInputException extends Exception {

	private static final long serialVersionUID = -6529062557326644044L;
	
	public InvalidInputException() {
		super();
	}

	public InvalidInputException(String message) {
		super(message);
	}
	
	public InvalidInputException(String message, Throwable cause) {
		super(message,cause);
	}

}
