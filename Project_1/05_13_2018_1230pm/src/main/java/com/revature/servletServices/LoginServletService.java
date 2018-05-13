package com.revature.servletServices;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.employee.Employee;
import com.revature.manager.Manager;
import com.revature.service.EmployeeService;
import com.revature.service.ManagerService;

public class LoginServletService {

	private LoginServletService() {
	}

	public static String login(HttpServletRequest request, HttpServletResponse response) {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		Employee employee = new Employee();
		employee.setUsername(username);
		employee.setPassword(password);
		
		if (EmployeeService.login(employee)) {
			employee = EmployeeService.getEmployee(username);
			
			if(ManagerService.isManager(employee.getId())) {
				System.out.println("Employee ID: " + employee.getId());
				Manager manager = ManagerService.getManager(employee.getId());
				System.out.println("Manager: " + manager);
				request.getSession().setAttribute("authorizedUser", manager);
				return "/managerHome.do";
			}
			request.getSession().setAttribute("authorizedUser", employee);
			return "/home.do";
		}

		return "/index.jsp";
	}
}
