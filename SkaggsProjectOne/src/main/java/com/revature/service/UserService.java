package com.revature.service;

import javax.servlet.http.HttpServletRequest;

public class UserService {
	private UserService() {}
	
	public static String home(HttpServletRequest request) {
		return "/home.jsp";
	}
}
