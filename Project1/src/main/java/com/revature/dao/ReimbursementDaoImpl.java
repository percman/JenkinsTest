package com.revature.dao;

import org.apache.log4j.Logger;

import com.revature.model.Reimbursement;

public class ReimbursementDaoImpl implements ReimbursementDao{

	private static final Logger logger = Logger.getLogger(ReimbursementDaoImpl.class);

	private static ReimbursementDaoImpl instance;
	private ReimbursementDaoImpl() {}
	public static ReimbursementDaoImpl getInstance() {
		if(instance == null) {
			instance = new ReimbursementDaoImpl();
		}
		return instance;
	}
	@Override
	public boolean insertReimbursement(Reimbursement reimbursement) {
		// TODO Auto-generated method stub
		return false;
	}

}
