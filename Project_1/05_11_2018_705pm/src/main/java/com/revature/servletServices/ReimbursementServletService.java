package com.revature.servletServices;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.revature.service.ReimbursementService;
import com.revature.factory.Reimbursement;
import com.revature.factory.ReimbursementFactory;

public class ReimbursementServletService {

	private ReimbursementServletService() {}
	
	public static String submit(HttpServletRequest request, HttpServletResponse response) {
		
		Reimbursement rb = ReimbursementFactory.getReimbursement(Integer.parseInt(request.getParameter("request")));
		System.out.println(rb.toString());
		rb.setRequestor_id(Integer.parseInt(request.getParameter("employee_id")));
		rb.setAmount(Float.parseFloat(request.getParameter("amount")));
		rb.setMessage(request.getParameter("message"));
		
		System.out.println(rb.getCategory());
		ReimbursementService.submitRequest(rb);
		
		return "/request.do";
	}
	
	public static String approve(HttpServletRequest request, HttpServletResponse response) {
		
		int requestorId = Integer.parseInt(request.getParameter("popupRequestorId"));
		int reimbursementId = Integer.parseInt(request.getParameter("popupRequestId"));
		int managerId = Integer.parseInt(request.getParameter("popupManagerId"));
		
		ReimbursementService.approveReimbursement(requestorId, managerId, reimbursementId);
		
		return "/managerManage.do";
	}
}
