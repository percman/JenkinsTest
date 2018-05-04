package com.revature.model;

public class EmployeeFactory {

	//Factory that will create a new financial manager or otheremployee obj depending on the title
	public static Worker createEmployee(Employee employee) {
		switch(employee.getTitle()) {
			case "Financial Manager":
				return new FinancialManager(employee.getId(),employee.getTitle(),employee.getUsername(),employee.getPassword());
			default:
				return new OtherEmployee(employee.getId(),employee.getTitle(),employee.getUsername(),employee.getPassword());
		}
		
	}
	
}
