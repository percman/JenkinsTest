package com.revature.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.employee.GenericEmployee;

public class LoginService {

		private LoginService() {}
		
		public static String login(HttpServletRequest request, HttpServletResponse responce) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			//Login logic
			if(username.equals("daniel") & password.equals("password")) {
				GenericEmployee authorized = new GenericEmployee(username,password);
				request.getSession().setAttribute("authorizedUser", authorized);
				return "/home.do";
			}
			
			return "index.jsp";
		}
		public static String logout(HttpServletRequest request, HttpServletResponse responce) {
			request.getSession().setAttribute("authorizedUser", new GenericEmployee());
			return "index.jsp";
		}
	}
