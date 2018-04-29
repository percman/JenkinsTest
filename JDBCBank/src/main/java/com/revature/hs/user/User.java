package com.revature.hs.user;

import org.apache.log4j.Logger;
import org.json.JSONObject;

public abstract class User {
	private String userName;
	private String passwordHash;
	private boolean isLocked;
	private String role;
	private static final Logger logger = Logger.getLogger(User.class);
	
	
	public User(String userName, String passwordHash, String role) {
		this.userName = userName;
		this.passwordHash = passwordHash;
		this.isLocked = false;
		this.role = role;
	}
	
	public User(JSONObject jso) {
		this.userName = jso.getString("username");
		this.passwordHash = jso.getString("passwordHash");
		this.isLocked = jso.getBoolean("isLocked");
		this.role = jso.getString("role");
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

	public String getPasswordHash() {
		return passwordHash;
	}

	public String getUserName() {
		return userName;
	}

	public JSONObject toJSONObject() {
		JSONObject jso = new JSONObject();
		jso.put("username", userName);
		jso.put("passwordHash", passwordHash);
		jso.put("isLocked", isLocked);
		jso.put("role", role);
		return jso;
	};
}
