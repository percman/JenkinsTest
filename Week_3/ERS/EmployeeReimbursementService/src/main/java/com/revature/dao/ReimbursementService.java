package com.revature.dao;

import java.util.List;

import com.revature.exceptions.EmployeeNotFoundException;
import com.revature.reimbursement.Reimbursment;

public class ReimbursementService{
	private static ReimbursementDao dao = ReimbursementDaoImpl.getInstance();
	
	public boolean submitReimbursment(Reimbursment rebur) {
		return dao.submitReimbursment(rebur);
	}
	
	public boolean approveReimbursment(Reimbursment rebur) {
		return dao.approveReimbursment(rebur);
	}
	
	public boolean denyReimbursment(Reimbursment rebur) {
		return dao.denyReimbursment(rebur);
	}
	
	public Reimbursment getReimbursmentByName(String emp) throws EmployeeNotFoundException{
		return dao.getReimbursmentByName(emp);
	}
	
	public List<Reimbursment> getReimbursemnts(){
		return dao.getReimbursemnts();
	}
	
	public List<Reimbursment> getPendingReimbursemnts(){
		return dao.getPendingReimbursemnts();
	}
	
	public List<Reimbursment> getApprovedReimbursemnts(){
		return dao.getApprovedReimbursemnts();
	}
	
	public List<Reimbursment> getReimbursmentForEmployee(String emp){
		return dao.getReimbursmentForEmployee(emp);
	}
}
