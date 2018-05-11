package com.revature.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImpl;
import com.revature.daoservice.EmployeeService;
import com.revature.model.Employee;

public class LoginService {

	private LoginService() {}
	
	private static EmployeeDao dao = EmployeeDaoImpl.getInstance();

	
	public static String login(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Employee new_employee = new Employee(username,password);
				
		// Login logic
		if(EmployeeService.login(new_employee)) {
			Employee authorizedUser = EmployeeService.getEmployee(username);
			request.getSession().setAttribute("authorizedUser", authorizedUser);
			return "/home.do";
		}
		
		return "/index.jsp";
	}
	
}
