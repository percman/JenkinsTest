package com.revature.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserService {

	private UserService() {}
	
	public static String home(HttpServletRequest request, HttpServletResponse response) {
		
		return "/home.jsp";
	}
	
	public static String account(HttpServletRequest request, HttpServletResponse response) {
		return "/Project_1_account.jsp";
	}
	
	public static String updateUser(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("employee_username");	
		String firstname = request.getParameter("employee_firstname");
		String lastname = request.getParameter("employee_firstname");
		String password = request.getParameter("employee_password");
		
		System.out.println(username + "\n" + firstname + "\n" + lastname);
		
		return "/account.do";
	}
}
