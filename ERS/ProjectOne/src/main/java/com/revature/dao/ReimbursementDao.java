package com.revature.dao;

import java.util.List;

import com.revature.model.Reimbursement;

public interface ReimbursementDao {

	List<Reimbursement> getAllReimbursements();
	Reimbursement getReimbursement(String employeename);
	boolean updateReimbursement(Reimbursement reimbursement);
	boolean insertReimbursement(Reimbursement reimbursement);
}
