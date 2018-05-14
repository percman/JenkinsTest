package com.revature.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.logging.InvalidLoginException;
import com.revature.logging.LogThis;
import com.revature.model.Employee;
import com.revature.model.LoginFactory;
import com.revature.service.EmployeeService;
import com.revature.service.FinancialManagerService;
import com.revature.service.NavigationService;

public class MasterDispatcher {

	private MasterDispatcher() {
	}

	public static String process(HttpServletRequest request, HttpServletResponse response) {

		switch (request.getRequestURI()) {
		case "/EmployeeReimbursementSystem/main.do":
			return NavigationService.main(request);
		case "/EmployeeReimbursementSystem/login.do":
			return NavigationService.login(request);
		case "/EmployeeReimbursementSystem/logout.do":
			return NavigationService.logout(request);
		case "/EmployeeReimbursementSystem/finManHome.do":
			if (request.getSession(false) != null) {
				return FinancialManagerService.finManHome(request);
			}
		case "/EmployeeReimbursementSystem/employeeHome.do":
			if (request.getSession(false) != null) {
				return EmployeeService.employeeHome(request);
			}
		case "/EmployeeReimbursementSystem/404.do":
			return NavigationService.fnf(request);
		default:
			LogThis.info("The request URI was: " + request.getRequestURI());
			LogThis.info("Returning 404 from default");
			return NavigationService.fnf(request);

		}

	}

}
