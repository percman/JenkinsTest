package com.revature.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.service.EmployeeService;

public class Dispatcher {
	
	private Dispatcher() {}
	
	
	public static String process(HttpServletRequest request,HttpServletResponse response) {
		switch(request.getRequestURI()) {
			case "/ERS/HTML/login.do":
				return EmployeeService.login(request, response);
			default:
				return "404.jsp";
		}
	}

}
