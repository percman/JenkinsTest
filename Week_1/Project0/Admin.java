package com.revature.bankapplication;

import java.io.Serializable;

public class Admin extends User implements Serializable {

	
	private static final long serialVersionUID = 7290608925449286667L;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	

}
