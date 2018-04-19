package com.revature.exceptions;

public class ChildException extends ParentException {

	private static final long serialVersionUID = -2322394118684856425L;
	
	// 3 constructors
	public ChildException() {
		super();
	}
	
	public ChildException(String msg) {
		super(msg);
	}
	
	public ChildException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
