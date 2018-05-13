package model;

import design.Person;

public class Employee extends Person{
	public Employee(int employeeid, int managerid, String username, String password) {
		super(employeeid, managerid, username, password);
	}
	
	@Override
	public String toString() {
		return "Employee [employeeid=" + super.getEmployeeid() + ", managerid=" + super.getManagerid() + ", username=" + super.getUsername()
				+ ", password=" + super.getPassword() + "]";
	}
	
}
