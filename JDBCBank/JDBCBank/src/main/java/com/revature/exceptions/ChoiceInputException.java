package com.revature.exceptions;

public class ChoiceInputException extends Exception {

	private static final long serialVersionUID = 5464810455639465156L;
	private String msg = "Invalid Choice Select";
	
	public ChoiceInputException() {}
	
	public ChoiceInputException(String msg) {
		this.msg = msg;
	}
	
	public String getMessage() {
		return msg;
	}
	
	
}
