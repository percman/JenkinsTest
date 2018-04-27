package com.revature.project0;

public class NoMovieException extends Exception{
	private static final long serialVersionUID = 3200001176101744269L;
	
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
