package com.revature.service;

import javax.servlet.http.HttpServletRequest;

import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.logging.LogThis;
import com.revature.model.Employee;
import com.revature.model.Reimbursement;

public class ReimbursementService {

	private static ReimbursementDao dao = ReimbursementDaoImpl.getInstance();

	private ReimbursementService() {
	}

	public static String newReimbursement(HttpServletRequest request) {
		
		
		String filePath = "C:/Users/Maxwell/Desktop/RevatrueBootcamp/1804-jta/ERS/ers_workspace/EmployeeReimbursementSystem/src/main/resources" + request.getParameter("receiptImage");
		LogThis.info(filePath);
		
		Employee temp = (Employee) request.getSession().getAttribute("currentEmployee");		
		
		System.out.println("the id given the the employee " + temp.getId());
		
		Reimbursement reimbursement = new Reimbursement(temp.getId(), request.getParameter("category"),
				request.getParameter("amount"));
		
		if (dao.newReimbursement(reimbursement, filePath) ) {
			if (temp.isFinancialManager()) {
				return "fmSubmitReimb.jsp";
			} else if (!temp.isFinancialManager()){
				return "eSubmitReimb.jsp";
			} else {
				
			}
		}
		return "404.jsp";
	}

}
