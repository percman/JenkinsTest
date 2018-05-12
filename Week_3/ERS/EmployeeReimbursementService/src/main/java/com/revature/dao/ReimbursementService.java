package com.revature.dao;

import java.util.List;

import com.revature.exceptions.EmployeeNotFoundException;
import com.revature.reimbursement.Reimbursment;

public class ReimbursementService{
	private static ReimbursementDao dao = ReimbursementDaoImpl.getInstance();
	
	public static boolean submitReimbursment(Reimbursment rebur) {
		return dao.submitReimbursment(rebur);
	}
	
	public static boolean approveReimbursment(int appId, int reburId) {
	   return dao.approveReimbursment(appId, reburId);
	}
	
	public static boolean denyReimbursment(int appId, int reburId) {
		return dao.denyReimbursment(appId, reburId);
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
	public static Reimbursment getReimbursmentById(int id) {
		return dao.getReimbursmentById(id);
	}
}
