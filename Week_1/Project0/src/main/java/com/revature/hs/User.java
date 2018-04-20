package com.revature.hs;

public abstract class User {
	private String userName;
	private int passwordHash;
	private boolean isLocked;
	private String role;
	
	
	
	public User(String userName, int passwordHash) {
		this.userName = userName;
		this.passwordHash = passwordHash;
		this.isLocked = false;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isLocked() {
		return isLocked;
	}

	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}
	
	
}
