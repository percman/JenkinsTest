package com.revature.dao;

import java.util.List;

import com.revature.exceptions.EmployeeNotFoundException;
import com.revature.reimbursement.Reimbursment;

public interface ReimbursementDao {
	public boolean submitReimbursment(Reimbursment rebur);
	
	public boolean approveReimbursment(Reimbursment rebur);
	
	public boolean denyReimbursment(Reimbursment rebur);
	
	public Reimbursment getReimbursmentByName(String emp) throws EmployeeNotFoundException;
	
	public List<Reimbursment> getReimbursemnts();
	
	public List<Reimbursment> getPendingReimbursemnts();
	
	public List<Reimbursment> getApprovedReimbursemnts();
	
	public List<Reimbursment> getReimbursmentForEmployee(String emp);
}
