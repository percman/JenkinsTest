package com.revature.exceptions;

public class ParentException extends Exception{

	private static final long serialVersionUID = -3850014859025891397L;
	
	
	/*
	 * To create your own custom exception, you need to provide 3 constructors
	 * - public no-arg constructor
	 * - public constructor that takes a String
	 * - public constructor that takes a String and a throwable
	 */
	
	// Public no-arg
	public ParentException() {
		super();
	}
	
	// Public constructor that takes a String
	public ParentException(String msg) {
		super(msg);
	}
	
	// Public constructor that takes a string and a throwable
	public ParentException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}
