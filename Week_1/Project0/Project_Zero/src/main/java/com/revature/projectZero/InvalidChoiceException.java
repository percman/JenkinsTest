package com.revature.projectZero;

public class InvalidChoiceException extends Exception{

	private static final long serialVersionUID = 5649297287268095732L;
	
	/*
	 * To create your own custom exception, you need to provide 3 constructors
	 * - public no-arg constructor
	 * - public constructor that takes a String
	 * - public constructor that takes a String and a throwable
	 */
	
	public InvalidChoiceException() {
		super();
	}
	
	public InvalidChoiceException(String msg) {
		super(msg);
	}
	
	public InvalidChoiceException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
