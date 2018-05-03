package com.revature.dao;

import java.util.List;

import com.revature.employee.Employee;
import com.revature.factory.Reimbursement;

public interface ReimbursementDao {

	boolean submitRequest(Reimbursement rb);
	Reimbursement getRequests(Employee employee);
	List<Reimbursement> getAllRequests();
}
