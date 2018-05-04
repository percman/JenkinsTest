package com.revature.service;

import com.revature.dao.EmployeeDAO;
import com.revature.daoimpl.EmployeeDaoImpl;
import com.revature.model.Employee;
import com.revature.model.EmployeeFactory;
import com.revature.model.Worker;

public class EmployeeService {
	private static EmployeeDAO dao = new EmployeeDaoImpl();
	
	private EmployeeService() {}
	
	public static Employee getEmployee(String username) {
		return dao.getEmployee(username);
	}
	
	public static Worker login(Employee employee) {
		Employee temp = dao.getEmployee(employee.getUsername());
		if(temp.getPassword().equals(dao.getPasswordHash(employee))) {
			return EmployeeFactory.createEmployee(employee);
		}
		return null;
	}
	
}
