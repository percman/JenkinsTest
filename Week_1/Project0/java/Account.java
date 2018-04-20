package com.revature.project_0;

import java.io.Serializable;

public abstract class Account implements Serializable {

	/*
	 * Both the admin account and the user accounts inherit from this abstract class.
	 * This allows me to use the Deserialization method on both admin and user objects since the are both children of the Account class
	 */
	private static final long serialVersionUID = -5107509073246097022L;
	private String userName;
	private String password;
	private boolean permissions;
	
	public Account() {
		
	}
	
	public Account(String name,String password,boolean permissions) {
		super();
		this.userName = name;
		this.password = password;
		this.permissions = permissions;
	}
	
	public boolean isAdmin() {
		return permissions;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + (permissions ? 1231 : 1237);
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (permissions != other.permissions)
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account [userName=" + userName + ", password=" + password + "]";
	}
	
	

}
