package com.revature.project0;

public class LockedAccountException extends Exception{
	private static final long serialVersionUID = 925607534058303810L;
	
	public LockedAccountException() {
		super();
	}
	public LockedAccountException(String msg) {
		super(msg);
	}
	public LockedAccountException(String msg, Throwable cause) {
		super(msg,cause);
	}

}
