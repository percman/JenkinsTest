package com.revature.service;

import com.revature.dao.ReimbursementDAO;
import com.revature.daoImpl.ReimbursementDaoImpl;
import com.revature.model.Employee;
import com.revature.model.Reimbursement;

public class ReimbursementService {
	private static ReimbursementDAO dao = new ReimbursementDaoImpl();
	
	private ReimbursementService() {}
	
	
	public static Reimbursement getReimbursement(int id) {
		return dao.getReimbursement(id);
	}
	
	public static boolean setReimbursement(Employee rbmt) {
		return dao.setReimbursement(rbmt);
	}
	
	public static boolean UpdateReimbursement(Reimbursement rbmt,Employee emp) {
		return dao.UpdateReimbursement(rbmt, emp);
	}
}
