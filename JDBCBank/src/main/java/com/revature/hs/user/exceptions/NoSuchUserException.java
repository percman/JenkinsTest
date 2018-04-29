package com.revature.hs.user.exceptions;

public class NoSuchUserException extends Exception {

	private static final long serialVersionUID = -3264749653918703288L;
	
	public NoSuchUserException() {
		super();
	}
	
	public NoSuchUserException(String s) {
		super(s);
	}
}
