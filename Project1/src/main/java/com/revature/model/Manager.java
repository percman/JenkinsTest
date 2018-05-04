package com.revature.model;

public class Manager extends Employee{

	private static final long serialVersionUID = 3639210872307779061L;

	public Manager() {}
	
	public Manager(String username, String password, String firstname, char middleInit, String lastName) {
		super(username, password, firstname, middleInit, lastName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Manager [toString()=" + super.toString() + ", hashCode()=" + hashCode() + ", getUsername()="
				+ getUsername() + ", getPassword()=" + getPassword() + ", getFirstname()=" + getFirstname()
				+ ", getMiddleInit()=" + getMiddleInit() + ", getLastName()=" + getLastName() + ", getClass()="
				+ getClass() + "]";
	}

}
