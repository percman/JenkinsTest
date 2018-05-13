package model;

import design.Person;

public class Manager extends Person {
	public Manager(int employeeid, int managerid, String username, String password) {
		super(employeeid, managerid, username, password);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Manager [employeeid=" + super.getEmployeeid() + ", managerid=" + super.getManagerid() + ", username=" + super.getUsername()
				+ ", password=" + super.getPassword() + "]";
	}
	
}