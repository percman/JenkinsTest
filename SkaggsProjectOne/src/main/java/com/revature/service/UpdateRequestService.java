package com.revature.service;

import javax.servlet.http.HttpServletRequest;

import com.revature.dao.Employee;
import com.revature.dao.EmployeeService;

public class UpdateRequestService {

	public static String update(HttpServletRequest request) {
		
		String approval = request.getParameter("approval");
		int  inputId = Integer.parseInt(request.getParameter("inputId"));
		Employee employee = (Employee) request.getSession().getAttribute("authorizedUser");
		EmployeeService.updateRequest(employee, inputId, approval);
		return "/requestFm.jsp";
	}
}
