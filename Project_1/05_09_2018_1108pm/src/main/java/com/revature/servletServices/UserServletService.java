package com.revature.servletServices;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.employee.Employee;
import com.revature.service.EmployeeService;

public class UserServletService {

	private UserServletService() {}
	
	public static String home(HttpServletRequest request, HttpServletResponse response) {
		
		return "/home.jsp";
	}
	
	public static String account(HttpServletRequest request, HttpServletResponse response) {
		return "/Project_1_account.jsp";
	}
	
	public static String request(HttpServletRequest request, HttpServletResponse response) {
		return "/Project_1_requests.jsp";
	}
	
	public static String updateUser(HttpServletRequest request, HttpServletResponse response) {
		Employee employee = new Employee();
		String id = request.getParameter("employee_id");
		
		employee = EmployeeService.getEmployee(Integer.parseInt(id));
		
		String username = request.getParameter("employee_username");	
		String firstname = request.getParameter("employee_firstname");
		String lastname = request.getParameter("employee_lastname");
		
		System.out.println("Employee information received");
		
		employee.setUsername(username);
		employee.setFirstname(firstname);
		employee.setLastname(lastname);
		EmployeeService.modifyUser(employee);
		
		request.getSession().setAttribute("authorizedUser", employee);
		
		System.out.println("Employee Modified");
		
		return "/account.do";
	}
}
