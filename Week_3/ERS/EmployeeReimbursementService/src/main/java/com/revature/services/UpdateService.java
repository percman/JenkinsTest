package com.revature.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.EmployeeService;
import com.revature.dao.ManagerService;
import com.revature.employee.FinanceManager;
import com.revature.employee.GenericEmployee;
import com.revature.exceptions.EmployeeNotFoundException;

public class UpdateService {
private UpdateService(){}
	
	public static String updateEmployee(HttpServletRequest request, HttpServletResponse response) {
		GenericEmployee emp = (GenericEmployee)request.getSession().getAttribute("authorizedUser");
		String fName = request.getParameter("firstname");
		String lName = request.getParameter("lastname");
		String email = request.getParameter("email");
		String add = request.getParameter("address");
		try {
			EmployeeService.updateInfo(emp.getId(), fName, lName, email, add);
		} catch (EmployeeNotFoundException e) {
			e.printStackTrace();
		}
		refreshEmployee(request,response);
		return"/employeeInfo.jsp";
	}
	
	public static String updateManager(HttpServletRequest request, HttpServletResponse response) {
		FinanceManager man = (FinanceManager)request.getSession().getAttribute("authorizedUser");
		String fName = request.getParameter("firstname");
		String lName = request.getParameter("lastname");
		String email = request.getParameter("email");
		String add = request.getParameter("address");
		
		try {
			ManagerService.updateInfo(man.getId(), fName, lName, email, add);
		} catch (EmployeeNotFoundException e) {
			e.printStackTrace();
		}
		refreshManager(request,response);
		return"/managerInfo.jsp";
	}

	public static String refreshManager(HttpServletRequest request, HttpServletResponse responce) {
		FinanceManager man = (FinanceManager)request.getSession().getAttribute("authorizedUser");
		try {
			request.getSession().setAttribute("authorizedUser", ManagerService.getManager(man.getUsername()));
		} catch (EmployeeNotFoundException e) {
			e.printStackTrace();
		}
		return"/managerInfo.jsp";
	}
	public static String refreshEmployee(HttpServletRequest request, HttpServletResponse responce) {
		GenericEmployee emp = (GenericEmployee)request.getSession().getAttribute("authorizedUser");
		try {
			request.getSession().setAttribute("authorizedUser", EmployeeService.getEmployee(emp.getUsername()));
		} catch (EmployeeNotFoundException e) {
			e.printStackTrace();
		}
		return"/employeeInfo.jsp";
	}
}
