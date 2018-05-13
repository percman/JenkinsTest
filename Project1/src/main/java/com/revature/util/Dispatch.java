package com.revature.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.revature.service.EmployeeService;
import com.revature.service.LoginService;
import com.revature.service.ReimbursementService;

public class Dispatch {

	private Dispatch() {}
	
	public static String process(HttpServletRequest request, HttpServletResponse response) {
		switch(request.getRequestURI()) {
		case "/Project1/login.do":
			return LoginService.login(request, response);
		case "/Project1/logout.do":
			return LoginService.logout(request, response);
		case "/Project1/update.do":
			return EmployeeService.updateEmployee(request, response);
		case "/Project1/addReimburse.do":
			return ReimbursementService.insertReimbursement(request, response);
		case "/Project1/viewPending.do":
			return EmployeeService.listPending(request, response);
		case "/Project1/viewApproved.do":
			return EmployeeService.listResolved(request, response);
		default: return "/404.jsp";
		}
	}
}
