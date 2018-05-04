package com.revature.dao;

import java.util.List;

import com.revature.model.Employee;
import com.revature.model.Manager;
import com.revature.model.Reimbursement;

public interface ManagerDao {

	boolean insertManager(Manager manager);
	
	boolean approveDeny(String response, int reimburseId, int managerId);
	
	List<Employee> viewEmployees();
	
	List<Reimbursement> viewReimbursements();
	
	List<Reimbursement> viewReimbursementByEmployee(Employee employee);
	
	Manager approver(Reimbursement reimbursement);
}
