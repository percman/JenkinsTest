package com.revature.ajax;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.EmployeeService;

public class AjaxDispatcher {
	private AjaxDispatcher() {}
	
	public static Object process(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException {
		switch(request.getRequestURI()) {
		case "/SkaggsProjectOne/getAllEmployees.ajax":
			return EmployeeService.getAllEmployees();
		case "/SkaggsProjectOne/getAllRequests.ajax":
			System.out.println("We are in the ajax disptcher");
			return EmployeeService.getAllRequests();
		default:
			return new String("Not implemented");
		}
	}
}
