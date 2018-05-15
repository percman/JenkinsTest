package com.revature.service;

import javax.servlet.http.HttpServletRequest;

import com.revature.dao.Employee;
import com.revature.dao.EmployeeService;
import com.revature.reimbursement.Reimbursement;

public class UpdateRequestService {

	public static String update(HttpServletRequest request) throws ClassNotFoundException {
		
		String approval = request.getParameter("approval");
		int  inputId = Integer.parseInt(request.getParameter("inputId"));
		Employee employee = (Employee) request.getSession().getAttribute("authorizedUser");
		Reimbursement r = EmployeeService.getRequest(inputId);
		System.out.println("Employee ID " + employee.getEmployeeId());
		System.out.println("Input ID " + r.getRequesterId());
		if (employee.getEmployeeId() != r.getRequesterId() ) {
			EmployeeService.updateRequest(employee, inputId, approval);
		}
		return "/requestFm.jsp";
	}
}
