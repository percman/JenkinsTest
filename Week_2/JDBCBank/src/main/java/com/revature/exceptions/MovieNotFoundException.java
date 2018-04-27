package com.revature.exceptions;

public class MovieNotFoundException extends Exception {
	private static final long serialVersionUID = 7854841564574540746L;

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
