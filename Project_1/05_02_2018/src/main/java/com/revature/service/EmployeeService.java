package com.revature.service;

import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImplementation;
import com.revature.employee.Employee;

public class EmployeeService {

	private static EmployeeDao dao = new EmployeeDaoImplementation();
	
	private EmployeeService() {}
	
	public static boolean insertUser(Employee employee) {
		return dao.insertUser(employee);
	}
}
