package com.revature.users.usertypes;

import com.revature.users.User;

public class Admin extends User{

	private static final long serialVersionUID = -590741263244204223L;
	
	private boolean adminstatus;

	public boolean isAdminstatus() {
		return adminstatus;
	}

	public void setAdminstatus(boolean adminstatus) {
		this.adminstatus = adminstatus;
	}
	
	public Admin(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		this.adminstatus = true;
	}


	public Admin(int id, String username, String password, boolean locked, double balance) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.adminstatus = true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (adminstatus ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Admin other = (Admin) obj;
		if (adminstatus != other.adminstatus)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Admin [adminstatus=" + adminstatus + "]";
	}
	
	
}
