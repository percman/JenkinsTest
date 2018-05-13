package com.revature.service;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public class NavigationService {

	private NavigationService() {}
	
	public static String fnf(HttpServletRequest request) {
		return "404.jsp";
	}
}
