package com.revature.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.LoginDao;
import com.revature.dao.LoginDaoImpl;
import com.revature.model.Employee;

public class LoginService {
	private LoginService() {}
	private static LoginDao dao = LoginDaoImpl.getInstance();
	
	public static String getPassword(Employee employee) {
		return dao.getPassword(employee);
	}
	public static String getPasswordHash(Employee employee) {
		return dao.getPasswordHash(employee);
	}
	
	public static String login(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Employee employee=EmployeeService.getEmployee(username);
		if(EmployeeService.getEmployee(username)!=null){
			System.out.println(getPassword(employee));
			System.out.println(getPasswordHash(new Employee(username, password)));
			if(getPassword(employee).equals(getPasswordHash(new Employee(username, password)))) {
				Employee authorizedUser=employee;
				request.getSession().setAttribute("authorizedUser", authorizedUser);
				return "/employee.jsp";
			}
		}
		return "/index.html";
	}
	
	public static String logout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute("authorizedUser");
		return "/index.html";
	}
}
