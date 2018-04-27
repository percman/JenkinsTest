package com.revature.exceptions;

public class AlreadyHaveMovieException extends Exception {
	private static final long serialVersionUID = 5423048731088460635L;

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
