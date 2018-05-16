package com.revature.model;

public abstract class User {
	private int id;
	private String username;
	private String password;
	private String firstname;
	private char middleInit;
	private String lastName;
	public User() {}
	public User(String username, String password) {
		this.username=username;
		this.password=password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public char getMiddleInit() {
		return middleInit;
	}
	public void setMiddleInit(char middleInit) {
		this.middleInit = middleInit;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
}
