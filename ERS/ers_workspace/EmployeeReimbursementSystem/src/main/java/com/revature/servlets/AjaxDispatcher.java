package com.revature.servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.model.Employee;
import com.revature.service.EmployeeService;
import com.revature.service.FinancialManagerService;

public class AjaxDispatcher {

	private AjaxDispatcher() {}
	
	public static Object process(HttpServletRequest request, HttpServletResponse response) {
		Employee temp = (Employee) request.getSession().getAttribute("currentEmployee");
		switch(request.getRequestURI()) {
		case "/EmployeeReimbursementSystem/getPending.ajax":
			return EmployeeService.viewReimbursementByStatus(temp, 1);
			
		case "/EmployeeReimbursementSystem/getApproved.ajax":
			return EmployeeService.viewReimbursementByStatus(temp, 2);
			
		case "/EmployeeReimbursementSystem/getDenied.ajax":
			return EmployeeService.viewReimbursementByStatus(temp, 3);
			
		case "/EmployeeReimbursementSystem/getEmployees.ajax":
			return FinancialManagerService.viewAllEmployees();
			
		case "/EmployeeReimbursementSystem/getAllReimbs.ajax":
			return FinancialManagerService.viewAllReimbursements();

		default:
			return new String("Not implemented");
		}
	}

}
