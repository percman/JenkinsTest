package com.revature.service;

import javax.servlet.http.HttpServletRequest;

import com.revature.factories.LoginFactory;
import com.revature.logging.InvalidLoginException;
import com.revature.logging.LogThis;
import com.revature.model.Employee;

public class NavigationService {

	private NavigationService() {}
	
	public static String login(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Employee temp = new Employee(username, password);
		LogThis.info("temp in login in NavigationService " + temp.toString());
		
		try {
			Employee currentEmployee = LoginFactory.userLogin(temp);
			LogThis.info("currentEmployee in login in NavigationService " + currentEmployee.toString());
			request.getSession().setAttribute("currentEmployee", currentEmployee);
			if(currentEmployee.isFinancialManager()) {
				return "finManHome.do";
			} else {
				return "employeeHome.do";
			}
		} catch (InvalidLoginException e) {
			LogThis.warn(e.getMessage());
			return "404.do";
		}
	}
	
	public static String fnf(HttpServletRequest request) {
		return "404.do";
	}
	
	public static String logout(HttpServletRequest request) {
		request.getSession().removeAttribute("currentEmployee");
		request.getSession().invalidate();
		return "main.do";
	}
	public static String main (HttpServletRequest request) {
		return "login.jsp";
	}
	

	public static String userUpdate(HttpServletRequest request) {
		Employee temp = (Employee) request.getSession().getAttribute("currentEmployee");
		int id = temp.getId();
		String username = request.getParameter("username");
		boolean isFinancialManager = temp.isFinancialManager();
		String firstname = request.getParameter("firstname");
		String middleInitial = request.getParameter("middleInitial");
		String lastname = request.getParameter("lastname");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
	
		Employee new_current = new Employee(id, username, isFinancialManager, firstname, middleInitial, lastname, phone, email);
		EmployeeService.updateEmployee(new_current);
		request.getSession().setAttribute("currentEmployee", new_current);
		return "changeAccountInfo.jsp";
	}
	
}
