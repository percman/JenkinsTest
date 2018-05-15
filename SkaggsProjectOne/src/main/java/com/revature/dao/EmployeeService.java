package com.revature.dao;

import java.sql.Blob;
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
	public static boolean updateEmployeeWithoutPassword(Employee e) {
		return dao.updateEmployeeWithoutPassword(e);
	}
	public static ArrayList<Reimbursement> getAllRequests() throws ClassNotFoundException {
		return dao.getAllRequests();
	}
	public static boolean updateRequest(Employee e, int reid, String approved) {
		return dao.updateRequest(e, reid, approved);
	}
	public static ArrayList<Reimbursement> getMyRequests(Employee e) throws ClassNotFoundException {
		return dao.getMyRequests(e);
	}
	public static byte[] getImage(int reid) {
		return dao.getImage(reid);
	}
	public static Reimbursement getRequest(int id) throws ClassNotFoundException {
		return dao.getRequest(id);
	}
}
