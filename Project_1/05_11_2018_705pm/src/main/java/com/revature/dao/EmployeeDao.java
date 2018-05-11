package com.revature.dao;

import java.util.List;

import com.revature.employee.Employee;

public interface EmployeeDao {

	Employee getEmployee(int id);
	List<Employee> getAllEmployees();
	boolean insertUser(Employee employee);
	boolean modifyUser(Employee employee);
	String getPasswordHash(Employee employee);
	Employee getEmployee(String username);
}
