package com.revature.service;

import java.util.List;

import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.model.Reimbursement;

public class ReimbursementService {

	private static ReimbursementDao dao = ReimbursementDaoImpl.getInstance();

	private ReimbursementService() {}
	
	public static List<Reimbursement> getAllReimbursements(){
		return dao.getAllReimbursements();
	}
	
	public static Reimbursement getReimbursement(String employeename) {
		return dao.getReimbursement(employeename);
	}
	
	public static boolean insertReimbursement(Reimbursement reimbursement) {
		return dao.insertReimbursement(reimbursement);
	}
	
	public static boolean updateReimbursement(Reimbursement reimbursement) {
		return dao.updateReimbursement(reimbursement);
	}
}
