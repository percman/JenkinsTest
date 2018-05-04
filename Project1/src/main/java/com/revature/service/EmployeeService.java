package com.revature.service;

import java.util.List;

import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImpl;
import com.revature.model.Employee;
import com.revature.model.Reimbursement;

public class EmployeeService {

	private EmployeeService() {}
	private static EmployeeDao dao = EmployeeDaoImpl.getInstance();
	public static boolean insertEmployee(Employee employee) {
		return dao.insertEmployee(employee);
	}
	
	public static boolean viewEmployee(Employee employee) {
		return dao.viewEmployee(employee);
	}
	
	public static boolean updateEmployee(Employee employee) {
		return dao.updateEmployee(employee);
	}
	
	public static List<Reimbursement> listPending(Employee employee){
		return dao.listPending(employee);
	}
	
	public static List<Reimbursement> listResolved(Employee employee){
		return dao.listResolved(employee);
	}
}
