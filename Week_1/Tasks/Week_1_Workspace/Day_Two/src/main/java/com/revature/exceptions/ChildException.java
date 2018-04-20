package com.revature.exceptions;

public class ChildException extends ParentException {

	private static final long serialVersionUID = -2322394118684856425L;
	
	// 3 constructors
	
	// public no-arg constructor
	public ChildException() {
		super();
	}
	
	// public constructor that takes a String
	public ChildException(String msg) {
		super(msg);
	}
	
	// public constructor that takes a String and a Throwable
	public ChildException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
