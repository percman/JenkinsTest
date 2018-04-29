package com.revature.model;

public class Admin extends Person{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6065239180076826871L;
	public Admin() {}
	public Admin(String username, String password) {
		super(username, password);
	}
	public Admin(String username, String password, boolean approved) {
		super(username, password, approved);
	}
}
