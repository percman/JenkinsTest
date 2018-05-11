package com.revature.servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.service.EmployeeService;
import com.revature.service.ReimbursementService;

public class AjaxDispatcher {

	private AjaxDispatcher() {}
	
	public static Object process(HttpServletRequest request, HttpServletResponse response) {
		
		switch(request.getRequestURI()) {
		case "/Project_1/getAllRequests.ajax": return ReimbursementService.getAllRequests();
		case "/Project_1/getAllEmployees.ajax": return EmployeeService.getAllEmployees();
		default: return new String("Not implemented");
		}
	}
}
