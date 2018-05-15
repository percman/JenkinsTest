package model;

import design.Person;

public class Manager extends Person {
	public Manager(int managerid, String username, String password) {
		super(managerid, username, password);
	}

	@Override
	public String toString() {
		return "Manager [managerid=" + super.getId() + ", username=" + super.getUsername()
				+ ", password=" + super.getPassword() + "]";
	}
	
}