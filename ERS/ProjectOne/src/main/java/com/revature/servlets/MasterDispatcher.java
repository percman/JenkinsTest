package com.revature.servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.service.LoginService;
import com.revature.service.UserService;

public class MasterDispatcher {

	private MasterDispatcher() {}
	
	public static String process(HttpServletRequest request, HttpServletResponse response) {
		switch(request.getRequestURI()) {
		case "/ProjectOneWeb/login.do":
			return LoginService.login(request, response);
		case "/ProjectOneWeb/home.do":
			return UserService.home(request, response);
		default:
			return "404.jsp";
		}
	}
}
