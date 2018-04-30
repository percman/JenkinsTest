package com.bank.exception;

public class UserNotFoundException extends Exception{
	
	private static final long serialVersionUID = -8032888743313142174L;

	public UserNotFoundException() {
		super();
	}
	
	public UserNotFoundException(String msg) {
		super(msg);
	}
	
	public UserNotFoundException(String msg, Throwable reason) {
		super(msg, reason);
	}
}
