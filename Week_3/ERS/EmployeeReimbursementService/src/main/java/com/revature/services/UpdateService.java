package com.revature.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.EmployeeService;
import com.revature.dao.ManagerService;
import com.revature.exceptions.EmployeeNotFoundException;

public class UpdateService {
private UpdateService(){}
	
	public static String updateEmployee(HttpServletRequest request, HttpServletResponse response) {
		String fName = request.getParameter("username");
		String lName = request.getParameter("password");
		String email = request.getParameter("email");
		String add = request.getParameter("add");
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			EmployeeService.updateInfo(id, fName, lName, email, add);
		} catch (EmployeeNotFoundException e) {
			e.printStackTrace();
		}
		return"/employeeInfo.jsp";
	}
	
	public static String updateManager(HttpServletRequest request, HttpServletResponse response) {
		String fName = request.getParameter("username");
		String lName = request.getParameter("password");
		String email = request.getParameter("email");
		String add = request.getParameter("add");
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			ManagerService.updateInfo(id, fName, lName, email, add);
		} catch (EmployeeNotFoundException e) {
			e.printStackTrace();
		}
		return"/managerInfo.jsp";
	}
}
