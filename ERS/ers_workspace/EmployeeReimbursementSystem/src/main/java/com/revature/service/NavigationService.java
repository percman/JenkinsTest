package com.revature.service;

import javax.servlet.http.HttpServletRequest;

import com.revature.logging.InvalidLoginException;
import com.revature.logging.LogThis;
import com.revature.model.Employee;
import com.revature.model.LoginFactory;

public class NavigationService {

	private NavigationService() {}
	
	public static String login(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Employee temp = new Employee(username, password);
		LogThis.info("temp in login in NavigationService " + temp.toString());
		
		try {
			Employee currentEmployee = LoginFactory.userLogin(temp);
			LogThis.info("currentEmployee in login in NavigationService " + currentEmployee.toString());
			request.getSession().setAttribute("currentEmployee", currentEmployee);
			if(currentEmployee.isFinancialManager()) {
				return "finManHome.jsp";
			} else {
				return "employeeHome.jsp";
			}
		} catch (InvalidLoginException e) {
			LogThis.warn(e.getMessage());
			return "404.jsp";
		}
	}
	
	public static String fnf(HttpServletRequest request) {
		return "404.jsp";
	}
}
