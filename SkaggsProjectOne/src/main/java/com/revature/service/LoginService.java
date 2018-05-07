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
		
		// Login logic
    	ArrayList<Employee> eList = EmployeeService.getAllEmployees();
		for (Employee e : eList) {
			System.out.println("employee" + e);
			System.out.println(e.isFinanceManager());
			if (username.equals(e.getUserName())) {
				if(e.isFinanceManager()) {
					Employee authorized = e;
					request.getSession().setAttribute("authorizedUser", authorized);
					return "/fm.do";
				}
				else {
					Employee authorized = e;
					request.getSession().setAttribute("authorizedUser", authorized);
					return "/home.do";
				}
			}	
		}

		return "/index.jsp";
	}
}
