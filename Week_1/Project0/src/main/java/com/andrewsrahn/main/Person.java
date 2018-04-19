package com.andrewsrahn.main;

public abstract class Person{
	private String name;
	private String email;
	private String password;
	private boolean locked;
	
	public boolean isLocked() {
		return locked;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
}
