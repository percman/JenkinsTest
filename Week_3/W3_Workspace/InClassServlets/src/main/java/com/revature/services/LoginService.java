package com.revature.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.model.User;

public class LoginService {

	private LoginService() {}
	
	public static String login(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		// Login Logic
		if(username.equals("amelia") & password.equals("password")) {
			User authorizedUser = new User(username, password, "Amelia", "Dunn", "test@gmail.com");
			request.getSession().setAttribute("authorizedUser", authorizedUser);
			return "/home.do";
		}
		
		return "index.jsp";
	}
}
