package com.revature.tokenizer;

import java.time.LocalDate;

public class Employee {

	private int id;
	private String firstName;
	private String lastName;
	private LocalDate hireDate;
	
	public Employee(int id, String firstName, String lastName, LocalDate hireDate) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.hireDate = hireDate;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", hireDate=" + hireDate
				+ "]";
	}
	
	
}
