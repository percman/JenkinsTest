package com.revature.dao;

import java.util.List;

import com.revature.employee.Employee;

public interface EmployeeDao {

	Employee getEmployee(int id);
	List<Employee> getAllEmployees();
	String getPasswordHash();
	boolean insertUser(Employee employee);
	boolean modifyUser();
	String getPasswordHash(Employee employee);
	Employee getEmployee(String username);
}
