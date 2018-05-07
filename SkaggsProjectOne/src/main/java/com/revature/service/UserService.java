package com.revature.service;

import javax.servlet.http.HttpServletRequest;

public class UserService {
	private UserService() {}
	
	public static String home(HttpServletRequest request) {
		return "/home.jsp";
	}
	public static String fm(HttpServletRequest request) {
		return "/fm.jsp";
	}
}
