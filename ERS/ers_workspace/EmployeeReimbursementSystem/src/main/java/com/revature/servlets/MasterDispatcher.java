package com.revature.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.factories.LoginFactory;
import com.revature.logging.InvalidLoginException;
import com.revature.logging.LogThis;
import com.revature.model.Employee;
import com.revature.service.EmployeeService;
import com.revature.service.FinancialManagerService;
import com.revature.service.NavigationService;
import com.revature.service.ReimbursementService;

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
			return FinancialManagerService.finManHome(request);
			
		case "/EmployeeReimbursementSystem/employeeHome.do":
			return EmployeeService.employeeHome(request);
			
		case "/EmployeeReimbursementSystem/update.do":
			return NavigationService.userUpdate(request);
			
		case "/EmployeeReimbursementSystem/submitReimb.do":
			return ReimbursementService.newReimbursement(request);
			
		case "/EmployeeReimbursementSystem/404.do":
			return NavigationService.fnf(request);
			
		default:
			LogThis.info("The request URI was: " + request.getRequestURI());
			LogThis.info("Returning 404 from default");
			return NavigationService.fnf(request);

		}

	}

}
