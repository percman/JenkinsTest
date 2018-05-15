package model;

import design.Person;

public class Employee extends Person{
	public Employee(int id, String username, String password) {
		super(id, username, password);
	}

	@Override
	public String toString() {
		return "Employee []";
	}
}
