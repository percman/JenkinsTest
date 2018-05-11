package com.revature.dao;

import java.util.List;

import com.revature.model.Employee;
import com.revature.model.Reimbursement;

public interface ReimbursementDAO {
	boolean setReimbursement(Employee rbm);
	Reimbursement getReimbursement(int id);
	boolean UpdateReimbursement(int r, Employee e);
	List<Reimbursement> getEmployeeReimbursement(Employee id);
	List<Reimbursement> ManagerFetch(Employee emp);
}
