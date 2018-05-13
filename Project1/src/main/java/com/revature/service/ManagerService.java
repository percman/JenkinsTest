package com.revature.service;

import java.util.List;

import com.revature.dao.ManagerDao;
import com.revature.dao.ManagerDaoImpl;
import com.revature.model.Employee;
import com.revature.model.Manager;
import com.revature.model.Reimbursement;

public class ManagerService {
	private ManagerService() {}
	private static ManagerDao dao = ManagerDaoImpl.getInstance();
	
	public static boolean insertManager(Manager manager){
		return dao.insertManager(manager);
	}
	
	public static boolean approveDeny(String response, int reimbursement_id, int manager_id) {
		return dao.approveDeny(response, reimbursement_id, manager_id);
	}
	
	public static List<Employee> viewEmployees(){
		return dao.viewEmployees();
	}
	
	public static List<Reimbursement> viewReimbursements(){
		return dao.viewReimbursements();
	}
	
	public static List<Reimbursement> viewReimbursementByEmployee(Employee employee){
		return dao.viewReimbursementByEmployee(employee);
	}
	
	public static String approver(int id) {
		return dao.approver(id);
	}
}
