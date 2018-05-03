package com.revature.dao;

import java.util.ArrayList;

public class EmployeeService {

	private static EmployeeDao dao = new EmployeeDaoImpl();
	
	public static Employee getEmployee(int id) {
		return dao.getEmployee(id);
	}
	public static ArrayList<Employee> getAllEmployee() {
		return dao.getAllEmployees();
	}
}
