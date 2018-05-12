package com.revature.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.daoservice.EmployeeDaoService;
import com.revature.model.Employee;

public class EmployeeService {

	public static String updateemployee(HttpServletRequest request, HttpServletResponse response) {

		Employee employee = (Employee) request.getSession().getAttribute("authorizedUser");
		
		try {			
			if(request.getParameter("updateusername") != "")
				employee.setUsername(request.getParameter("updateusername"));
			
			if(request.getParameter("updatepassword") != "")
				employee.setPassword(request.getParameter("updatepassword"));
			
			if(request.getParameter("updatefirstname") != "")
				employee.setFirstname(request.getParameter("updatefirstname"));
			
			if(request.getParameter("updatelastname") != "")
				employee.setLastname(request.getParameter("updatelastname"));
			
			if(request.getParameter("updateemail") != "")
				employee.setEmail(request.getParameter("updateemail"));
						
			if(request.getParameter("updatephonenumer") != null)
				employee.setPhonenumber(Long.parseLong(request.getParameter("updatephonenumer")));
						
			System.out.println("Updated information? " + EmployeeDaoService.updateEmployee(employee));
			
		} catch(NullPointerException npe) {
			npe.getMessage();
		}
		
		return "/home.jsp";
	}

	
}
