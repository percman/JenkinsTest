package com.revature.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.model.Employee;

public class LoginService {
	public static String login(HttpServletRequest request, HttpServletResponse response) {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if(username.equals("william") & password.equals("password")) {
			Employee employee = new Employee(username,password);
			request.getSession().setAttribute("authorizedUser", employee);
			return "/home.do";
		}
	
			return "/index.do";
	}
}