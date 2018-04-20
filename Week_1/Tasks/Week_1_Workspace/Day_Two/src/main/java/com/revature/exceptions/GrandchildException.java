package com.revature.exceptions;

public class GrandchildException extends ChildException {

	private static final long serialVersionUID = 4069150908591605991L;

	// 3 constructors
	
	// public no-arg constructor
	public GrandchildException() {
			super();
		}

	// public constructor that takes a String
	public GrandchildException(String msg) {
			super(msg);
		}

	// public constructor that thakes a String and a Throwable
	public GrandchildException(String msg, Throwable cause) {
			super(msg, cause);
		}
}
