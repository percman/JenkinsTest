package com.revature.service;

import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.model.Reimbursement;

public class ReimbursementService {

	private ReimbursementService() {}
	private static ReimbursementDao dao = ReimbursementDaoImpl.getInstance();
	
	public static boolean insertReimbursement (Reimbursement reimbursement) {
		return dao.insertReimbursement(reimbursement);
	}
	
	
}
