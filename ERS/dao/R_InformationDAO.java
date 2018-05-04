package com.revature.dao;

import com.revature.model.Reimbursement;

public interface R_InformationDAO {
	boolean setInformation(Reimbursement reimbursement);
	Reimbursement getInformation(int id);
}
