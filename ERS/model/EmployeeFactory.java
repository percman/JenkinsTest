package com.revature.model;

public class EmployeeFactory {

	
	public static String page(Employee employee) {
		switch(employee.getTitle()) {
			case "Financial Manager":
				return "/HTML/FinancialManagerHome.html";
			default:
				return "/HTML/GenericEmployeeHomePage.jsp";
		}
		
	}
	
}
