package com.revature.exception;

public class UserNotFoundException extends Exception {

	private static final long serialVersionUID = 3229977694456258115L;
	
	public UserNotFoundException() {
		super();
	}

	public UserNotFoundException(String message) {
		super(message);
	}
	
	public UserNotFoundException(String message, Throwable cause) {
		super(message,cause);
	}

}
