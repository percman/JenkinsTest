package com.revature.service;

import java.util.List;

import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementDaoImplementation;
import com.revature.employee.Employee;
import com.revature.factory.Reimbursement;

public class ReimbursementService {

	private static ReimbursementDao dao = new ReimbursementDaoImplementation();

	private ReimbursementService() {
	}

	public static boolean submitRequest(Reimbursement rb) {
		return dao.submitRequest(rb);
	}
	
	public static List<Reimbursement> getRequests(int id) {
		return dao.getRequests(id);
	}
	
	public static List<Reimbursement> getAllRequests() {
		return dao.getAllRequests();
	}
	
	public static boolean approveReimbursement(int requestorId, int managerId, int reimbursementId) {
		return dao.approveReimbursement(requestorId, managerId, reimbursementId);
	}
	
	public static boolean denyReimbursement(int requestorId, int managerId, int reimbursementId) {
		return dao.denyReimbursement(requestorId, managerId, reimbursementId);
	}
}