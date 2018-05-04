package com.revature.dao;

import java.util.ArrayList;

import com.revature.reimbursement.Reimbursement;

public class EmployeeService {

	private static EmployeeDao dao = new EmployeeDaoImpl();
	
	public static Employee getEmployee(int id) {
		return dao.getEmployee(id);
	}
	public static ArrayList<Employee> getAllEmployee() {
		return dao.getAllEmployees();
	}
	public static boolean insertEmployee(Employee e) {
		return dao.insertEmployee(e);
	}
	public static boolean insertRequest(Reimbursement r) {
		return dao.insertRequest(r);
	}
}
