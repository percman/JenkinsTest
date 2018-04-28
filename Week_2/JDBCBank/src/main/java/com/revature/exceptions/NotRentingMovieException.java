package com.revature.exceptions;

public class NotRentingMovieException extends Exception {
private static final long serialVersionUID = 925607534058303810L;
	
	public NotRentingMovieException() {
		super();
	}
	public NotRentingMovieException(String msg) {
		super(msg);
	}
	public NotRentingMovieException(String msg, Throwable cause) {
		super(msg,cause);
	}
}
