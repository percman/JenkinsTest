package com.revature.service;


import java.util.List;

import com.revature.employeedao.EmployeeDao;
import com.revature.employeedao.EmployeeDaoImpl;
import com.revature.logs.LogHere;
import com.revature.model.Employee;

public class EmployeeService {

	private static EmployeeDao dao = EmployeeDaoImpl.getInstance();
	
	private EmployeeService() {}
	
	public static List<Employee> getAllEmployees() {
		return dao.getAllEmployees();
	}
	
	public static List<Employee> getAllManagers() {
		return dao.getAllManagers();
	}
	
	public static Employee getEmployee(String username) {
		return dao.getEmployee(username);
	}
	
	public static Employee getAnyEmployee() {
		return dao.getAnyEmployee();
	}

	public static boolean insertEmployee(Employee user) {
		return dao.insertEmployee(user);
	}
	
	public static boolean updateEmployee(Employee user) {
		return dao.updateEmployee(user);
	}
	
	
	public static Employee login(Employee employee) {
		Employee newemployee = dao.getEmployee(employee.getUsername());
		
		try {		
			
			if(newemployee.getPassword().equals(dao.getPasswordHash(employee))){
				System.out.println("You are a valid user, " + newemployee.getUsername());
				return newemployee;
			}
		} catch(NullPointerException npe) {
			LogHere.warn(npe.getMessage());
			System.out.println("The username or password combination is invalid.");
			return null;
		}

		System.out.println("The username or password combination is invalid.");
		return null;
	}
	
}
