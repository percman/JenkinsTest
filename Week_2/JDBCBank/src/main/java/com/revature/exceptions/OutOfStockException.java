package com.revature.exceptions;

public class OutOfStockException extends Exception {
private static final long serialVersionUID = 925607534058303810L;
	
	public OutOfStockException() {
		super();
	}
	public OutOfStockException(String msg) {
		super(msg);
	}
	public OutOfStockException(String msg, Throwable cause) {
		super(msg,cause);
	}
}
