package com.revature.service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.model.Employee;
import com.revature.model.Reimbursement;

public class ReimbursementService {

	private ReimbursementService() {}
	private static ReimbursementDao dao = ReimbursementDaoImpl.getInstance();
	
	public static String insertReimbursement (HttpServletRequest request, HttpServletResponse response) {
		Employee employee=(Employee)request.getSession().getAttribute("authorizedUser");
		int id=employee.getId();
		Reimbursement reimbursement=new Reimbursement(id, request.getParameter("category"), Double.parseDouble(request.getParameter("amount")));
		if(dao.insertReimbursement(reimbursement)) {
			return "/viewPending.do";
		}
		else return "ReimbursementSubmit.jsp";
	}
	
	
}
