package com.revature.service;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import com.revature.*;
import com.revature.dao.Employee;
import com.revature.dao.EmployeeService;

public class LoginService {
	private LoginService() {}
	
	public static String login(HttpServletRequest request) throws ClassNotFoundException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Employee test = new Employee(username, password);
		// Login logic
    	Employee e = EmployeeService.getEmployee(username);
		String hashedPassword = EmployeeService.getPasswordHash(test);
		if (username.equals(e.getUserName()) & hashedPassword.equals(e.getPassword())) {
			
			if (e.isFinanceManager()) {
				Employee authorized = e;
				request.getSession().setAttribute("authorizedUser", authorized);
				return "/fm.do";
			} else {
				Employee authorized = e;
				request.getSession().setAttribute("authorizedUser", authorized);
				return "/home.do";
			}
		}	

		return "/index.jsp";
	}
}
