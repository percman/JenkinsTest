package com.revature.model;

import java.io.Serializable;

public abstract class Person implements Serializable{

	private static final long serialVersionUID = 7416812389728189408L;
	private String username;
	private String password;
	private boolean approved;

	
	public Person() {}
	
	public Person(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		this.approved = false;
	}

	public Person(String username, String password, boolean approved) {
		super();
		this.username=username;
		this.password=password;
		this.approved=approved;
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

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (approved ? 1231 : 1237);
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		Person other = (Person) obj;
		if (approved != other.approved)
			return false;
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

	@Override
	public String toString() {
		return "Person [username=" + username + ", password=" + password + ", approved=" + approved + "]";
	}


}
