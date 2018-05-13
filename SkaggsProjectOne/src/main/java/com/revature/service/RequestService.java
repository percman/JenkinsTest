package com.revature.service;

import javax.servlet.http.HttpServletRequest;

import com.revature.dao.Employee;
import com.revature.dao.EmployeeService;
import com.revature.reimbursement.Reimbursement;

public class RequestService {

	public static String reqest(HttpServletRequest request) throws ClassNotFoundException {
		String requestType = request.getParameter("requestType");
		int amount = Integer.parseInt(request.getParameter("amount"));
		Employee employee = (Employee) request.getSession().getAttribute("authorizedUser");
		System.out.println("request " + requestType);
		System.out.println("amount " + amount );
		Reimbursement r = new Reimbursement(0, employee.getEmployeeId(), 0, requestType, 0, amount, "", "", "", "", "", "", null);
		EmployeeService.insertRequest(r);
		return "home.jsp";
	}
}
