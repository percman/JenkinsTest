package com.revature.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.employee.Employee;
import com.revature.service.EmployeeService;

public class LoginService {

	private LoginService() {
	}

	public static String login(HttpServletRequest request, HttpServletResponse response) {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		Employee employee = new Employee();
		employee.setUsername(username);
		employee.setPassword(password);
		
		if (EmployeeService.login(employee)) {
			employee = EmployeeService.getEmployee(username);
			request.getSession().setAttribute("authorizedUser", employee);
			return "/home.do";
		}

		return "/index.jsp";
	}
}
