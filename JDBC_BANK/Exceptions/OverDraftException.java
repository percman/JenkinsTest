package com.revature.exception;

public class OverDraftException extends Exception {

	private static final long serialVersionUID = 5657000169409832019L;
	
	public OverDraftException() {
		super();
	}

	public OverDraftException(String message) {
		super(message);
	}
	
	public OverDraftException(String message,Throwable cause) {
		super(message,cause);
	}

}
