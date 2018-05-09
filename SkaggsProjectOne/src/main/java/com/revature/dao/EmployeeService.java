package com.revature.dao;

import java.util.ArrayList;

import com.revature.reimbursement.Reimbursement;

public class EmployeeService {

	private static EmployeeDao dao = new EmployeeDaoImpl();
	
	public static Employee getEmployee(int id) throws ClassNotFoundException {
		return dao.getEmployee(id);
	}
	public static Employee getEmployee(String username) throws ClassNotFoundException {
		return dao.getEmployee(username);
	}
	public static ArrayList<Employee> getAllEmployees() throws ClassNotFoundException {
		return dao.getAllEmployees();
	}
	public static boolean insertEmployee(Employee e) throws ClassNotFoundException {
		return dao.insertEmployee(e);
	}
	public static boolean insertRequest(Reimbursement r) throws ClassNotFoundException {
		return dao.insertRequest(r);
	}
	public static String getPasswordHash(Employee employee) {
		return dao.getPasswordHash(employee);
	}
	public static boolean updateEmployee(Employee e) {
		return dao.updateEmployee(e);
	}
}
