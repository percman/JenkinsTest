package com.revature.servletServices;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ManagerServletService {

	private ManagerServletService() {}
	
	public static String home(HttpServletRequest request, HttpServletResponse response) {
		return "/Project_1_ManagerHome.jsp";
	}
}
