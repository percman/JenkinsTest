package com.revature.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImpl;
import com.revature.model.Employee;

public class LoginService {

	private LoginService() {}
	
	private static EmployeeDao dao = EmployeeDaoImpl.getInstance();

	
	public static String login(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Employee new_employee = new Employee(username,password);
		Employee employee = dao.getEmployee(username);

		
		
		
		// Login logic
		if(EmployeeService.login(employee)) {
			Employee authorizedUser = new Employee(username, password);
			request.getSession().setAttribute("authorizedUser", authorizedUser);
			return "/home.do";
		}
		
		return "/index.jsp";
	}
	
}
