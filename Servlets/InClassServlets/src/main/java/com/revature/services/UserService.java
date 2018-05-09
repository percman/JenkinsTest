package com.revature.services;

import javax.servlet.http.HttpServletRequest;

public class UserService {

	private UserService() {}
	
	public static String home(HttpServletRequest request) {
		return "/home.jsp";
	}
}
