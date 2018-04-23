package com.andrewsrahn.main;

import java.io.Serializable;

public abstract class Person implements Serializable{
	private static final long serialVersionUID = -4458615592920058562L;
	private String name;
	private String password;
	
	public String getName() {
		return name;
	}
	public String getPassword() {
		return password;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
