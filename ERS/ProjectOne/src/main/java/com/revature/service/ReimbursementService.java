package com.revature.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.daoservice.ReimbursementDaoService;
import com.revature.factory.Reimbursement;
import com.revature.factory.ReimbursementFactory;
import com.revature.logs.LogHere;
import com.revature.model.Employee;

public class ReimbursementService {


	public static String insertreimbursement(HttpServletRequest request, HttpServletResponse response) {

		
		String new_category = request.getParameter("categoryselect");
		String new_amount = request.getParameter("reimbursementamount");
		
		Employee employee = (Employee) request.getSession().getAttribute("authorizedUser");
		
		Reimbursement newreimbursement = ReimbursementFactory.getReimbursement(new_category.toLowerCase());
		newreimbursement.setRequestor_id(employee.getId());
		newreimbursement.setAmount(Double.parseDouble(new_amount));
				

		System.out.println(ReimbursementDaoService.insertReimbursement(newreimbursement));
		
		return "/home_in.jsp";
	}

	
	
	
}
