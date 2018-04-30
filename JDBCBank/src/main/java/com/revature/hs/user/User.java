package com.revature.hs.user;

import org.apache.log4j.Logger;
import org.json.JSONObject;

public abstract class User {
	private int id;
	private String userName;
	private String password;
	private boolean isLocked;
	private String role;
	private static final Logger logger = Logger.getLogger(User.class);
	
	public User(String userName, String password, String role) {
		this.userName = userName;
		this.password = password;
		this.isLocked = false;
		this.role = role;
	}

	public User(int id, String username, boolean isLocked, String role) {
		this.id = id;
		this.userName = username;
		this.isLocked = isLocked;
		this.role = role;
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

	public String getPassword() {return this.password;}

	public void setPassword(String pw) {
		this.password = pw;
	}

	public String getUserName() {
		return userName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
