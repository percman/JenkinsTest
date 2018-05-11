package com.revature.dao;

import java.util.List;

import com.revature.model.Employee;
import com.revature.model.Reimbursement;

public interface EmployeeDao {

	Employee getEmployee(String username);
	boolean insertEmployee(Employee employee);
	boolean updateEmployee(Employee employee);
	String getPasswordHash(Employee employee);
	
	List<Reimbursement> viewAllReimbursements();
	List<Reimbursement> viewReimbursementByStatus(int status);
}
