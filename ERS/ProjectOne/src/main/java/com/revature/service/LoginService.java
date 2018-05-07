package com.revature.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.employeedao.EmployeeDao;
import com.revature.employeedao.EmployeeDaoImpl;

import com.revature.model.Employee;

public class LoginService {

	private LoginService() {}
	
	private static EmployeeDao dao = EmployeeDaoImpl.getInstance();

	
	public static String login(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Employee new_employee = new Employee(username,password);

		Employee employee = dao.getEmployee(username);
		
		// Login Logic 
		if((employee.getPassword()).equals(new_employee.getPassword())) {
			Employee authorizedUser = new Employee(username, password);
			request.getSession().setAttribute("authorized user", authorizedUser);
			return "/home.do";
		}
		
		return "/index.jsp";
	}
	
}
