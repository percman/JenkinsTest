package com.revature.exception;

public class NotValidUserException extends Exception{


	private static final long serialVersionUID = 7201425204378683632L;

	public NotValidUserException(){
		super();
	}
	
	public NotValidUserException(String msg) {
		super(msg);
	}
	
	public NotValidUserException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
