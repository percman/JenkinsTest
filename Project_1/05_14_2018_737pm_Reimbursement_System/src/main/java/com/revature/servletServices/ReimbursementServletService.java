package com.revature.servletServices;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.factory.Reimbursement;
import com.revature.factory.ReimbursementFactory;
import com.revature.image.Image;
import com.revature.service.ReimbursementService;

public class ReimbursementServletService {

	private ReimbursementServletService() {}
	
	public static String submit(HttpServletRequest request, HttpServletResponse response) {
		
		Reimbursement rb = ReimbursementFactory.getReimbursement(Integer.parseInt(request.getParameter("request")));
		rb.setRequestor_id(Integer.parseInt(request.getParameter("employee_id")));
		rb.setAmount(Float.parseFloat(request.getParameter("amount")));
		rb.setMessage(request.getParameter("message"));
		
		String file = "C:/Users/Allison/Documents/workspace-sts-3.9.4.RELEASE/Project_1/src/main/resources/" + request.getParameter("file");
		System.out.println(request.getParameter("file"));
		System.out.println("FILE NAME: " + file);
		ReimbursementService.submitRequest(rb, file);
		
		return "/request.do";
	}
	
	public static String mSubmit(HttpServletRequest request, HttpServletResponse response) {
		
		Reimbursement rb = ReimbursementFactory.getReimbursement(Integer.parseInt(request.getParameter("request")));
		rb.setRequestor_id(Integer.parseInt(request.getParameter("employee_id")));
		rb.setAmount(Float.parseFloat(request.getParameter("amount")));
		rb.setMessage(request.getParameter("message"));
		
		
		String file = "C:/Users/Allison/Documents/workspace-sts-3.9.4.RELEASE/Project_1/src/main/resources/" + request.getParameter("file");
		System.out.println(request.getParameter("file"));
		System.out.println("FILE NAME: " + file);
		ReimbursementService.submitRequest(rb, file);
		
		return "/managerRequest.do";
	}
	
	public static String approve(HttpServletRequest request, HttpServletResponse response) {
		
		int requestorId = Integer.parseInt(request.getParameter("popupRequestorId"));
		int reimbursementId = Integer.parseInt(request.getParameter("popupRequestId"));
		int managerId = Integer.parseInt(request.getParameter("popupManagerId"));
		int approved = Integer.parseInt(request.getParameter("request_radio"));
		System.out.println(approved);
		
		if(approved == 1) {
			ReimbursementService.approveReimbursement(requestorId, managerId, reimbursementId);
		}
		if(approved == -1) {
			ReimbursementService.denyReimbursement(requestorId, managerId, reimbursementId);
		}
		
		return "/managerManage.do";
	}
}
