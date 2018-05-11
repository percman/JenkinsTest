package com.revature.daoservice;

import java.util.List;

import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.factory.Reimbursement;

public class ReimbursementService {

	private static ReimbursementDao dao = ReimbursementDaoImpl.getInstance();

	private ReimbursementService() {}
	
	public static List<Reimbursement> getAllReimbursements(){
		return dao.getAllReimbursements();
	}
	
	public static Reimbursement getReimbursementFromEmployee(String employeename) {
		return dao.getReimbursementFromEmployee(employeename);
	}
	
	public static boolean insertReimbursement(Reimbursement reimbursement) {
		return dao.insertReimbursement(reimbursement);
	}
	
	public static boolean updateReimbursement(Reimbursement reimbursement) {
		return dao.updateReimbursement(reimbursement);
	}
}
