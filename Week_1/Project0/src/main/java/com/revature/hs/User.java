package com.revature.hs;

import org.json.JSONObject;

public abstract class User {
	private String userName;
	private int passwordHash;
	private boolean isLocked;
	private String role;
	
	
	
	public User(String userName, int passwordHash, String role) {
		this.userName = userName;
		this.passwordHash = passwordHash;
		this.isLocked = false;
		this.role = role;
	}
	
	public User(JSONObject jso) {
		this.userName = jso.getString("username");
		this.passwordHash = jso.getInt("passwordHash");
		this.isLocked = jso.getBoolean("isLocked");
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

	public int getPasswordHash() {
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
