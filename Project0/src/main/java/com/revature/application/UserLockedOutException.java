package com.revature.application;

public class UserLockedOutException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5061209929297751980L;

	public UserLockedOutException() {
		super("This account has been locked. Please talk to an employee to correct this issue.");
	}
	
}
