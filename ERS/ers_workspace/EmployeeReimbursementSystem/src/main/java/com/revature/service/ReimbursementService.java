package com.revature.service;

import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.model.Reimbursement;

public class ReimbursementService {

	private static ReimbursementDao dao = ReimbursementDaoImpl.getInstance();

	private ReimbursementService() {
	}
	
	public static boolean newReimbursement(Reimbursement reimbursement) {
		return dao.newReimbursement(reimbursement);
	}

}
