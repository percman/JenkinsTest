package com.revature.bank;

import java.io.Serializable;

public abstract class Person implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7416812389728189408L;
	String username;
	String password;
	boolean loggedIn;
	boolean approved;


}
