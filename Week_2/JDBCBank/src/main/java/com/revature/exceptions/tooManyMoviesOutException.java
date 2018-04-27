package com.revature.exceptions;

public class tooManyMoviesOutException extends Exception{
private static final long serialVersionUID = 925607534058303810L;
	
	public tooManyMoviesOutException() {
		super();
	}
	public tooManyMoviesOutException(String msg) {
		super(msg);
	}
	public tooManyMoviesOutException(String msg, Throwable cause) {
		super(msg,cause);
	}

}
