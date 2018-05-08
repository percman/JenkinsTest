package com.revature.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserService {

	private UserService() {}
	
	public static String home(HttpServletRequest request, HttpServletResponse response) {
		return "/home.jsp";
	}
}
