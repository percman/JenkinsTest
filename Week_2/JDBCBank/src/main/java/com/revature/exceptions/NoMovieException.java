package com.revature.exceptions;

public class NoMovieException extends Exception {
	private static final long serialVersionUID = 7370194969327377634L;
	
	public NoMovieException() {
		super();
	}
	public NoMovieException(String msg) {
		super(msg);
	}
	public NoMovieException(String msg, Throwable cause) {
		super(msg,cause);
	}
	
}
