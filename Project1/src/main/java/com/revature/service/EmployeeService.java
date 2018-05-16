package com.revature.service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImpl;
import com.revature.model.Employee;
import com.revature.model.User;

public class EmployeeService {

	private EmployeeService() {}
	private static EmployeeDao dao = EmployeeDaoImpl.getInstance();
	public static boolean insertEmployee(Employee employee) {
		return dao.insertEmployee(employee);
	}
	
	public static Employee viewEmployee(int id) {
		return dao.viewEmployee(id);
	}
	public static User getEmployee(String username) {
		return dao.getEmployee(username);
	}
	public static String updateEmployee(HttpServletRequest request, HttpServletResponse response) {
		Employee employee=(Employee)request.getSession().getAttribute("authorizedUser");
		employee.setUsername(request.getParameter("username"));
		employee.setPassword(request.getParameter("password"));
		employee.setFirstname(request.getParameter("firstName"));
		employee.setMiddleInit(request.getParameter("middleInitial").charAt(0));
		employee.setLastName(request.getParameter("lastName"));
		if(dao.updateEmployee(employee)) return "/employee.jsp";
		else return "/EmployeeUpdate.jsp";
	}
	
	public static String listPending(HttpServletRequest request, HttpServletResponse response){
		Employee employee=(Employee)request.getSession().getAttribute("authorizedUser");
		request.setAttribute("pendingList", dao.listPending(employee));
		return "/EmployeePending.jsp";
	}
	
	public static String listResolved(HttpServletRequest request, HttpServletResponse response){
		Employee employee=(Employee)request.getSession().getAttribute("authorizedUser");
		request.setAttribute("resolvedList", dao.listResolved(employee));
		return "/EmployeeApproved.jsp";
	}
	
	public static String getName(int id) {
		return dao.getName(id);
	}
}
