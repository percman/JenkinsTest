package com.revature.model;

import java.io.Serializable;

public class Employee implements Serializable {
	private String username;
	private String password;
	private String firstname;
	private char middleInit;
	private String lastName;
	
	public Employee() {}
	
	public Employee(String username, String password, String firstname, char middleInit, String lastName) {
		super();
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.middleInit = middleInit;
		this.lastName = lastName;
	}
	@Override
	public String toString() {
		return "Employee [username=" + username + ", password=" + password + ", firstname=" + firstname
				+ ", middleInit=" + middleInit + ", lastName=" + lastName + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + middleInit;
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
		Employee other = (Employee) obj;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (middleInit != other.middleInit)
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
	private static final long serialVersionUID = -195102502914716002L;
}
