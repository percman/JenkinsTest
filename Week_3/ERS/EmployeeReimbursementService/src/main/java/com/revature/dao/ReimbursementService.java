package com.revature.dao;

import java.util.List;

import com.revature.exceptions.EmployeeNotFoundException;
import com.revature.reimbursement.Reimbursment;

public class ReimbursementService{
	private static ReimbursementDao dao = ReimbursementDaoImpl.getInstance();
	
	public static boolean submitReimbursment(Reimbursment rebur) {
		return dao.submitReimbursment(rebur);
	}
	
	public static boolean approveReimbursment(Reimbursment rebur) {
		return dao.approveReimbursment(rebur);
	}
	
	public static boolean denyReimbursment(Reimbursment rebur) {
		return dao.denyReimbursment(rebur);
	}
	
	public static Reimbursment getReimbursmentByName(String emp) throws EmployeeNotFoundException{
		return dao.getReimbursmentByName(emp);
	}
	
	public static List<Reimbursment> getReimbursemnts(){
		return dao.getReimbursemnts();
	}
	
	public static List<Reimbursment> getPendingReimbursemnts(){
		return dao.getPendingReimbursemnts();
	}
	
	public static List<Reimbursment> getApprovedReimbursemnts(){
		return dao.getApprovedReimbursemnts();
	}
	
	public static List<Reimbursment> getReimbursmentForEmployee(String emp){
		return dao.getReimbursmentForEmployee(emp);
	}
}
