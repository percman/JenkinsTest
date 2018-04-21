package com.revature.project0;

public class PasswordIncorrectException extends Exception{
	private static final long serialVersionUID = 925607534058303810L;
	
	public PasswordIncorrectException() {
		super();
	}
	public PasswordIncorrectException(String msg) {
		super(msg);
	}
	public PasswordIncorrectException(String msg, Throwable cause) {
		super(msg,cause);
	}

}