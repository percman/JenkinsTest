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
	
	public static Reimbursement getRequests(Employee employee) {
		return dao.getRequests(employee);
	}
	
	public static List<Reimbursement> getAllRequests() {
		return dao.getAllRequests();
	}
}