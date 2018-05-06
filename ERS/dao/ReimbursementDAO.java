package com.revature.dao;

import com.revature.model.Employee;
import com.revature.model.Reimbursement;

public interface ReimbursementDAO {
	boolean setReimbursement(Employee rbm);
	Reimbursement getReimbursement(int id);
	boolean UpdateReimbursement(Reimbursement r, Employee e);
}
