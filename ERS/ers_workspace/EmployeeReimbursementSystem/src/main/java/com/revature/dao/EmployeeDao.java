package com.revature.dao;

import java.util.List;

import com.revature.model.Employee;
import com.revature.model.Reimbursement;

public interface EmployeeDao {
	
	boolean isFinMan(String username);

	Employee getEmployee(String username);
	boolean updateEmployee(Employee employee);
	String getPasswordHash(Employee employee);
	
	List<Reimbursement> viewAllReimbursements(Employee employee);
	List<Reimbursement> viewReimbursementByStatus(Employee employee, int status);
	
	// Low priority
	boolean insertEmployee(Employee employee);

}
