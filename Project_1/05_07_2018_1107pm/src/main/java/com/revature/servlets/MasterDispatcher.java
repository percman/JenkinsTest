package com.revature.servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.services.LoginService;
import com.revature.services.UserService;

public class MasterDispatcher {

	private MasterDispatcher() {
	}

	public static String process(HttpServletRequest request, HttpServletResponse response) {

		System.out.println(request + " " + response);
		System.out.println(request.getRequestURI());
		switch (request.getRequestURI()) {

		case "/Project_1/update.do": return UserService.account(request, response);
		case "/Project_1/login.do": return LoginService.login(request, response);
		case "/Project_1/home.do": return UserService.home(request, response);
		case "/Project_1/account.do": return UserService.account(request, response);
		default:{
			System.out.println("bad");
			return "404.jsp";
		}			
		}
	}
}
