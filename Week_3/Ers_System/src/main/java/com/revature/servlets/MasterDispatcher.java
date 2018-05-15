package com.revature.servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.service.LoginService;


public class MasterDispatcher {
	
	private MasterDispatcher() {}
	
	public static String process(HttpServletRequest request, HttpServletResponse response) {
		
		switch(request.getRequestURI()) {
		
		case "/Ers_System/login.do":
				System.out.println("inside masterdispatcher " + request.getRequestURI());
				return LoginService.login(request, response);
		case "/Ers_System/home.do":
				return LoginService.login(request, response);
		default:
				return "404.jsp";
		}
	}
}
