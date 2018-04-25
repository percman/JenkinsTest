package com.revature.project0;

public class MovieNotFoundException extends Exception {
	private static final long serialVersionUID = 925607534058303810L;
	
	public MovieNotFoundException() {
		super();
	}
	public MovieNotFoundException(String msg) {
		super(msg);
	}
	public MovieNotFoundException(String msg, Throwable cause) {
		super(msg,cause);
	}
}
