package com.revature.dao;

import java.util.List;

import com.revature.model.Employee;
import com.revature.model.Manager;
import com.revature.model.Reimbursement;

public interface ManagerDao {

	boolean insertManager(Manager manager);
	
	boolean approveDeny(String response, int reimburse_id, int manager_id);
	
	List<Employee> viewEmployees();
	
	List<Reimbursement> viewReimbursements();
	
	List<Reimbursement> viewReimbursementByEmployee(Employee employee);
	
	String approver(int id);
}
