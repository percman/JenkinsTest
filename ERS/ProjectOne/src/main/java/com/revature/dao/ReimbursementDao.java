package com.revature.dao;

import java.util.List;

import com.revature.factory.Reimbursement;

public interface ReimbursementDao {

	List<Reimbursement> getAllReimbursements();
	Reimbursement getReimbursementFromEmployee(String employeename);
	boolean updateReimbursement(Reimbursement reimbursement);
	boolean insertReimbursement(Reimbursement reimbursement);
}
