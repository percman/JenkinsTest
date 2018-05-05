package com.revature.service;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import com.revature.*;
import com.revature.dao.Employee;
import com.revature.dao.EmployeeService;

public class LoginService {
	private LoginService() {}
	
	public static String login(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		// Login logic
    	ArrayList<Employee> eList = EmployeeService.getAllEmployees();
		for (Employee e : eList) {
			if (username.equals(e.getUserName())) {
				Employee authorized = e;
				request.getSession().setAttribute("authorizedUser", authorized);
				return "/home.do";
			}	
		}

		return "/index.jsp";
	}
}
