package com.revature.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.EmployeeDAO;
import com.revature.daoImpl.EmployeeDaoImpl;
import com.revature.model.Employee;
import com.revature.model.EmployeeFactory;

public class EmployeeService {
	private static EmployeeDAO dao = new EmployeeDaoImpl();
	
	private EmployeeService() {}
	
	public static Employee getEmployee(String username) {
		return dao.getEmployee(username);
	}
	
	public static boolean check(Employee employee) {
		Employee temp = dao.getEmployee(employee.getUsername());
		if(temp.getPassword().equals(dao.getPasswordHash(employee))) {
			return true;
		}
		return false;
	}
	
	public static String login(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Employee temp = new Employee(username,password);
		if(check(temp)) {
			Employee user = getEmployee(username);
			request.getSession().setAttribute("Employee", user);
			return EmployeeFactory.page(user);
		}
		return "/HTML/GenericCorpIndex.html";
		
	}
	
}
