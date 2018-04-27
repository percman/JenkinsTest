package com.revature.exceptions;

public class UserNotFoundException extends Exception{
	private static final long serialVersionUID = 925607534058303810L;
	
	public UserNotFoundException() {
		super();
	}
	public UserNotFoundException(String msg) {
		super(msg);
	}
	public UserNotFoundException(String msg, Throwable cause) {
		super(msg,cause);
	}

}
