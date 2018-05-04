package com.revature.dao;

import java.util.List;

import com.revature.model.Employee;
import com.revature.model.Reimbursement;

public interface EmployeeDao {

	boolean insertEmployee(Employee employee);
	
	boolean viewEmployee(Employee employee);
	
	boolean updateEmployee(Employee employee);
	
	List<Reimbursement> listPending(Employee employee);
	
	List<Reimbursement> listResolved(Employee employee);
}
