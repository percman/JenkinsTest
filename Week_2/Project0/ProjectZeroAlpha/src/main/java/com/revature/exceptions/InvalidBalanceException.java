package com.revature.exceptions;

public class InvalidBalanceException extends Exception{

	private static final long serialVersionUID = 5464810455639465156L;
	private String msg = "Invalid Choice Select";
	
	public InvalidBalanceException() {}
	
	public InvalidBalanceException(String msg) {
		this.msg = msg;
	}
	
	public String getMessage() {
		return msg;
	}
}
