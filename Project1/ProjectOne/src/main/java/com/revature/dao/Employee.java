package com.revature.dao;

public class Employee {

	String firstName;
	char middleInit;
	String lastName;
	String userName;
	String password;
	
	public Employee() {}
	//Constructor excluding middle initial
	public Employee(String firstName, String lastName, String userName, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
	}

	//Constructor including middle initial
	public Employee(String firstName, char middleInit, String lastName, String userName, String password) {
		super();
		this.firstName = firstName;
		this.middleInit = middleInit;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
	}
	public Employee(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "Employee [firstName=" + firstName + ", middleInit=" + middleInit + ", lastName=" + lastName
				+ ", userName=" + userName + ", password=" + password + "]";
	}	
}
