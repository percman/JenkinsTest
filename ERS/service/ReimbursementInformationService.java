package com.revature.service;

import com.revature.dao.R_InformationDAO;
import com.revature.daoImpl.R_InformationDaoImpl;
import com.revature.model.Reimbursement;

public class ReimbursementInformationService {
	private static R_InformationDAO dao = new R_InformationDaoImpl();
	
	private ReimbursementInformationService() {}
	
	public Reimbursement getInformation(Reimbursement rbmt) {
		return dao.getInformation(rbmt);
	}
	
	public boolean newInformation(Reimbursement rbmt) {
		return dao.setInformation(rbmt);
	}
	
	public static boolean UpdateInformation(Reimbursement rbmt) {
		return dao.UpdateInformation(rbmt);
	}
}
