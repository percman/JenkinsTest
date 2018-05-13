package com.revature.service;

import javax.servlet.http.HttpServletRequest;

import com.revature.dao.Employee;
import com.revature.dao.EmployeeService;

public class RequestByEmployee {
	public static String request(HttpServletRequest request) throws ClassNotFoundException {
		String username = request.getParameter("employeeUsername");
		Employee e = EmployeeService.getEmployee(username);
		System.out.println("Employee " + e);
		request.getSession().setAttribute("requestedUser", e);
		
		return "/requestByEmployeeResult.jsp";
	}
}
