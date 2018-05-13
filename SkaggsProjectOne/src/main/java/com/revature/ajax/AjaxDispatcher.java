package com.revature.ajax;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.Employee;
import com.revature.dao.EmployeeService;

public class AjaxDispatcher {
	private AjaxDispatcher() {}
	
	public static Object process(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException {
		switch(request.getRequestURI()) {
		case "/SkaggsProjectOne/getAllEmployees.ajax":
			return EmployeeService.getAllEmployees();
		case "/SkaggsProjectOne/getAllRequests.ajax":
			return EmployeeService.getAllRequests();
		case"/SkaggsProjectOne/getMyRequests.ajax":
			Employee e = (Employee) request.getSession().getAttribute("authorizedUser");
			return EmployeeService.getMyRequests(e);
		case"/SkaggsProjectOne/getRequests.ajax":
			Employee requested = (Employee) request.getSession().getAttribute("requestedUser");
			return EmployeeService.getMyRequests(requested);
		default:
			return new String("Not implemented");
		}
	}
}
