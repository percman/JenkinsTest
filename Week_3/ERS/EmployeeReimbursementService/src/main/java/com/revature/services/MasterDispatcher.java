package com.revature.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MasterDispatcher {
private MasterDispatcher() {
		
	}
	
	public static String process(HttpServletRequest request, HttpServletResponse responce) {
		System.out.println(request.getRequestURI());
		switch(request.getRequestURI()) {
			
		case "/EmployeeReimbursementService/login.do": 
			return LoginService.login(request, responce);
			
			default: return "404.jsp";
		}
	}
}
