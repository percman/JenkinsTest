package com.revature.service;

import java.util.List;

import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.model.Employee;
import com.revature.model.Reimbursement;

public class ReimbursementService {

	private ReimbursementService() {}
	private static ReimbursementDao dao = ReimbursementDaoImpl.getInstance();
	
	public static boolean insertReimbursement (Reimbursement reimbursement) {
		return dao.insertReimbursement(reimbursement);
	}
	
	
}
