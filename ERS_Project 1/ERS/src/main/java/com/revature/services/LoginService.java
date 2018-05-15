package com.revature.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.dao.EmployeeDaoImpl;
import com.revature.model.Employee;

public class LoginService {

	
	public String login(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		EmployeeDaoImpl empdao = new EmployeeDaoImpl();
				
		// Login logic
		if(empdao.login(username, password)) {
			Employee authorizedUser = empdao.getEmployee(username);
			request.getSession().setAttribute("authorizedUser", authorizedUser);
			HttpSession sesh = request.getSession(true);
			sesh.setAttribute("emp", authorizedUser);
			return "/home.do";
		}
		
		return "/login.html";
	}
}
