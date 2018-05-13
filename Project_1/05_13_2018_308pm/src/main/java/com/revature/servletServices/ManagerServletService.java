package com.revature.servletServices;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ManagerServletService {

	private ManagerServletService() {}
	
	public static String home(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("pepperoni pizza");
//		ReimbursementServletService.updateImage(request, response);
		return "/Project_1_ManagerHome.jsp";
	}
	
	public static String manage(HttpServletRequest request, HttpServletResponse response) {	
		return "/Project_1_ManagerManagement.jsp";
	}
	
	public static String request(HttpServletRequest request, HttpServletResponse response) {	
		return "/Project_1_ManagerRequests.jsp";
	}
	
	public static String account(HttpServletRequest request, HttpServletResponse response) {	
		return "/Project_1_ManagerAccount.jsp";
	}
}
