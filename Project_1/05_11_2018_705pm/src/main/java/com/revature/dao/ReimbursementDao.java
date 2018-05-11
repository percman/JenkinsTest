package com.revature.dao;

import java.util.List;

import com.revature.employee.Employee;
import com.revature.factory.Reimbursement;

public interface ReimbursementDao {

	boolean submitRequest(Reimbursement rb);
	List<Reimbursement> getAllRequests();
	
	boolean approveReimbursement(int requestorId, int managerId, int reimbursementId);
	boolean denyReimbursement(int requestorId, int managerId, int reimbursementId);
	List<Reimbursement> getRequests(int id);
}
