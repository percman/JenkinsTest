package com.revature.dao;

import com.revature.model.Reimbursement;

public interface ReimbursementDAO {
	void setReimbursement(Reimbursement rbm);
	Reimbursement getReimbursement(int id);
}
