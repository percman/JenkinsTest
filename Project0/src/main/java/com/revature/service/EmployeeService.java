package com.revature.service;

import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImpl;
import com.revature.model.Employee;

public class EmployeeService {

	private static EmployeeDao dao = EmployeeDaoImpl.getInstance();
	
	private EmployeeService() {}
	
	public static Employee getEmployee(String username) {
		return dao.getEmployee(username);
	}

	public static boolean insertEmployee(Employee employee) {
		return dao.insertEmployee(employee);
	}
	
	public static boolean login(Employee employee) {
		Employee temp = dao.getEmployee(employee.getUsername());
		
		if(temp.getPassword().equals(dao.getPasswordHash(employee))) {
			return true;
		}
			return false;
	}
	
}
