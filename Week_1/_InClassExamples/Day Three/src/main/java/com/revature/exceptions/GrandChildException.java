package com.revature.exceptions;

public class GrandChildException extends ChildException {

	private static final long serialVersionUID = 3324125291948907870L;
	
	// 3 constructors
	public GrandChildException() {
		super();
	}
	
	public GrandChildException(String msg) {
		super(msg);
	}
	
	public GrandChildException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
