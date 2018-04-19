package com.revature.exceptions;

public class RecursiveException extends Exception {

	private static final long serialVersionUID = -3926018077301662180L;
	private String msg;
	
	public RecursiveException() {}
	
	public RecursiveException(String msg) {
		this.msg = msg;
	}
	public String getMessage() {
		return msg;
	}
}
