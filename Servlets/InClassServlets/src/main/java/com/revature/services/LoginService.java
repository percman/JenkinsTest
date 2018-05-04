package com.revature.services;

import javax.servlet.http.HttpServletRequest;

import com.revature.model.User;

public class LoginService {

	private LoginService() {}
	
	public static String login(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		// Login logic
		if (username.equals("william") & password.equals("password")) {
			User authorizedUser = new User(username, password, "William", "Gentry", "test@gmail.com");
			request.getSession().setAttribute("authorizedUser", authorizedUser);
			return "/home.do";
		}
		
		return "/index.jsp";
	}
}
