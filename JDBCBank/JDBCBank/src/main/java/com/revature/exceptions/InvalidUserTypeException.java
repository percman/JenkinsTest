package com.revature.exceptions;

public class InvalidUserTypeException extends Exception{

	private static final long serialVersionUID = 3488033931403880401L;
	
	public InvalidUserTypeException() {
		super();
	}
	
	public InvalidUserTypeException(String msg) {
		super(msg);
	}
	
	public InvalidUserTypeException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	@Override
	public String getMessage() {
		return "That is not a valid type of user.";
	}


}
