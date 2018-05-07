package com.revature.servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.service.LoginService;

public class MasterDispatcher {

	private MasterDispatcher() {}
	
	public static String process(HttpServletRequest request, HttpServletResponse response) {
		
		switch(request.getRequestURI()) {
			
			case "/InClassServlets/login.do":
				return LoginService.login(request, response);
				
			case "/InClassServlets/home.do":
				return LoginService.home(request, response);
			
			default:
				return "404.jsp";
		}
	}
}
