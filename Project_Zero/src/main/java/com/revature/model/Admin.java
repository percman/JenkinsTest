package com.revature.model;

import java.io.Serializable;

public class Admin extends User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7248941043354757686L;
	private String username;
	private String password;
	private boolean isAdmin;
	
	

	public Admin () {}
	
	public Admin(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		this.isAdmin = true;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void approve(Customer username) {
		
		username.setApprovalCode(1);
		
	}

	
	public void reject(Customer username) {
		
		username.setApprovalCode(-1);
		
	}

	
	public void lock(Customer username) {
		
		username.setLockCode(1);
	}

	
	public void unlock(Customer username) {
		
		username.setLockCode(-1);
	}
	
	public boolean getIsAdmin() {
		return isAdmin;
	}
	
	public void setIsAdmin(boolean b) {
		this.isAdmin = b;
	}
	
	

}
