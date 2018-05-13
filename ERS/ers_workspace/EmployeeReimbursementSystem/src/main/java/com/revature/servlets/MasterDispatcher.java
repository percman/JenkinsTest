package com.revature.servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.logging.InvalidLoginException;
import com.revature.logging.LogThis;
import com.revature.model.Employee;
import com.revature.model.LoginFactory;
import com.revature.service.EmployeeService;

public class MasterDispatcher {

	private MasterDispatcher() {
	}

	public static Object process(HttpServletRequest request, HttpServletResponse response) {
		switch (request.getRequestURI()) {
		case "/EmployeeReimbursementSystem/isFinMan":
			try {
				if (EmployeeService.isFinMan(request.getParameter("username"))) {
					return true;
				} else {
					return false;
				}
			} catch (InvalidLoginException ile) {
				LogThis.warn(ile.getMessage());
			}
		case "/EmployeeReimbursementSystem/login":
			String isFinMan = request.getParameter("isFinMan");
			Employee employee = new Employee(request.getParameter("username"), request.getParameter("password"));
			return LoginFactory.userLogin(isFinMan, employee);
		default:
			System.out.println("The request URI was: " + request.getRequestURI());
			return "404.jsp";

		}

	}

}
