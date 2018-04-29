package com.revature.exception;

public class InvalidInputException extends Exception{

	private static final long serialVersionUID = -3315569187537813730L;

	public InvalidInputException() {
		super();
	}
	
	public InvalidInputException(String msg) {
		super(msg);
	}
	
	public InvalidInputException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
