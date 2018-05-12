package com.revature.dao;

import java.util.List;

import com.revature.model.Employee;
import com.revature.model.FinancialManager;
import com.revature.model.Reimbursement;

public interface FinancialManagerDao {
	
	FinancialManager getFinancialManager(String username);
	
	List<Employee> viewAllEmployees();
	List<Reimbursement> viewAllReimbursements();
	List<Reimbursement> viewReimbursementsByEmployee(Employee employee);
	boolean resolveReimbursement(Reimbursement reimbursement, int status);
	
	// Low priority
	boolean insertFinancialManager(FinancialManager financialManager);

}
