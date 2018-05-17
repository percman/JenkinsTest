package com.revature.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.service.EmployeeService;
import com.revature.service.LoginService;
import com.revature.service.ManagerService;
import com.revature.service.ReimbursementService;

public class Dispatch {

	private Dispatch() {}
	
	public static String process(HttpServletRequest request, HttpServletResponse response) {
		if(!request.getRequestURI().equals("/Project1/index.jsp")){
			response.setHeader("Cache-Control","no-cache, no-store, must-revalidate"); 
			response.addHeader("Cache-Control", "post-check=0, pre-check=0");
			response.setHeader("Pragma","no-cache"); 
			response.setDateHeader ("Expires", 0);
		}
		switch(request.getRequestURI()) {
		case "/Project1/login.do":
			return LoginService.login(request, response);
		case "/Project1/logout.do":
			return LoginService.logout(request, response);
		case "/Project1/update.do":
			return EmployeeService.updateEmployee(request, response);
		case "/Project1/addReimburse.do":
			return ReimbursementService.insertReimbursement (request, response);
		case "/Project1/viewPending.do":
			return EmployeeService.listPending(request, response);
		case "/Project1/viewApproved.do":
			return EmployeeService.listResolved(request, response);
		case "/Project1/ManagerPending.do":
			return ManagerService.listPending(request, response);
		case "/Project1/approveDeny.do":
			return ManagerService.approveDeny(request, response);
		case "/Project1/ViewEmployees.do":
			return ManagerService.viewEmployees(request, response);
		case "/Project1/ViewEmployeeReimbursements.do":
			return ManagerService.viewReimbursementByEmployee(request, response);
		case "/Project1/AllReimbursements.do":
			return ManagerService.viewReimbursements(request, response);
		default: return "/404.jsp";
		}
	}
}
