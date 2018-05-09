package com.revature.service;

import javax.servlet.http.HttpServletRequest;

import com.revature.dao.Employee;
import com.revature.dao.EmployeeService;

public class UpdateService {
	private UpdateService() {}
	
	public static String update(HttpServletRequest request) {
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String password = request.getParameter("password");
		Employee employee = (Employee) request.getSession().getAttribute("authorizedUser");

		if (firstName != null && !firstName.isEmpty()) {
			employee.setFirstName(firstName);
		}
		if (lastName != null && !lastName.isEmpty()) {
			employee.setLastName(lastName);
		}
		if (password != null && !password.isEmpty()) {
			employee.setPassword(password);
		}
		System.out.println("Password null? " + password != null);
		EmployeeService.updateEmployee(employee);
		return "/update.jsp";
	}
}
