package com.revature.project0;

public class AlreadyHaveMovieException extends Exception {
	
private static final long serialVersionUID = 925607534058303810L;
	
	public AlreadyHaveMovieException() {
		super();
	}
	public AlreadyHaveMovieException(String msg) {
		super(msg);
	}
	public AlreadyHaveMovieException(String msg, Throwable cause) {
		super(msg,cause);
	}
}
