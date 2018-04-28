package com.revature.exceptions;

public class InvalidLoginException extends Exception {

	private static final long serialVersionUID = -7631937422769364670L;


	public InvalidLoginException() {
		super();
	}
	
	public InvalidLoginException(String msg) {
		super(msg);
	}
	
	public InvalidLoginException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	@Override
	public String getMessage() {
		return "Your username and/or password was incorrect.";
	}
	
}
