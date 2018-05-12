package com.revature.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.revature.dao.ReimbursementService;
import com.revature.employee.FinanceManager;
import com.revature.employee.GenericEmployee;
import com.revature.exceptions.ManagerApprovingOwnRequestException;
import com.revature.reimbursement.Category;
import com.revature.reimbursement.Reimbursment;

public class ReburService {
	private ReburService(){}
	
	public static String submitManagerRebur(HttpServletRequest request, HttpServletResponse response) {
		FinanceManager man = (FinanceManager)request.getSession().getAttribute("authorizedUser");
		String cat = request.getParameter("category");
		String amount = request.getParameter("amount");
		Reimbursment rebur = new Reimbursment(Category.stringToCat(cat), Integer.parseInt(amount),man.getId());
		ReimbursementService.submitReimbursment(rebur);
		String rebursementJson = new Gson().toJson(ReimbursementService.getReimbursmentForEmployee(man.getUsername()));
		request.getSession().setAttribute("reimbursements", rebursementJson);
		String pendingJson = new Gson().toJson(ReimbursementService.getPendingReimbursemnts());
		request.getSession().setAttribute("pendingReimbursements", pendingJson);
		return"/viewManagerReimburstment.jsp";
	}
	public static String submitEmployeeRebur(HttpServletRequest request, HttpServletResponse response) {
		GenericEmployee emp = (GenericEmployee)request.getSession().getAttribute("authorizedUser");
		String cat = request.getParameter("category");
		String amount = request.getParameter("amount");
		Reimbursment rebur = new Reimbursment(Category.stringToCat(cat), Integer.parseInt(amount),emp.getId());
		ReimbursementService.submitReimbursment(rebur);
		String rebursementJson = new Gson().toJson(ReimbursementService.getReimbursmentForEmployee(emp.getUsername()));
		request.getSession().setAttribute("reimbursements", rebursementJson);
		String pendingJson = new Gson().toJson(ReimbursementService.getPendingReimbursemnts());
		request.getSession().setAttribute("pendingReimbursements", pendingJson);
		return"/viewEmployeeReimburstment.jsp";
	}

	public static String approve(HttpServletRequest request, HttpServletResponse responce) throws ManagerApprovingOwnRequestException {
		FinanceManager man = (FinanceManager)request.getSession().getAttribute("authorizedUser");
		int appId = man.getId();
		int reburId = Integer.parseInt(request.getParameter("id"));
		int subId = ReimbursementService.getReimbursmentById(reburId).getSumbitterId();
		if(subId != appId) {
		ReimbursementService.approveReimbursment(appId,reburId);
		String pendingJson = new Gson().toJson(ReimbursementService.getPendingReimbursemnts());
		request.getSession().setAttribute("pendingReimbursements", pendingJson);
		return"/approveReimburstment.jsp";
		}
		throw new ManagerApprovingOwnRequestException();
	}
	public static String deny(HttpServletRequest request, HttpServletResponse responce) throws ManagerApprovingOwnRequestException {
		FinanceManager man = (FinanceManager)request.getSession().getAttribute("authorizedUser");
		int appId = man.getId();
		int reburId = Integer.parseInt(request.getParameter("id"));
		int subId = ReimbursementService.getReimbursmentById(reburId).getSumbitterId();
		if(subId != appId) {
		ReimbursementService.denyReimbursment(appId,reburId);
		String pendingJson = new Gson().toJson(ReimbursementService.getPendingReimbursemnts());
		request.getSession().setAttribute("pendingReimbursements", pendingJson);
		return"/approveReimburstment.jsp";
		}
		throw new ManagerApprovingOwnRequestException();
	}
}
