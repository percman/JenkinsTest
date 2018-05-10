package com.revature.service;

import java.util.List;

import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImplementation;
import com.revature.employee.Employee;

public class EmployeeService {

	private static EmployeeDao dao = new EmployeeDaoImplementation();

	private EmployeeService() {
	}

	public static boolean insertUser(Employee employee) {
		return dao.insertUser(employee);
	}

	public static Employee getEmployee(int id) {
		return dao.getEmployee(id);
	}
	
	public static Employee getEmployee(String username) {
		return dao.getEmployee(username);
	}

	public static List<Employee> getAllEmployees() {
		return dao.getAllEmployees();
	}
	
	public static boolean login(Employee employee) {
		Employee temp = dao.getEmployee(employee.getUsername());
		try {
			if (temp.getUsername() == null) {
				//throw new UsernameDoesNotExistException();
				System.out.println("login exception");
			}	
			else if (temp.getPassword().equals(dao.getPasswordHash(employee))) {
				System.out.println("Login successful");
				return true;
			}
			System.out.println("\n\n\t\t***INVALID PASSWORD***\n");
			return false;
		} catch (Exception udnee) {
			System.out.println("login exception");
			return false;
		}
	}
	
	public static boolean modifyUser(Employee employee) {
		return dao.modifyUser(employee);
	}
}
