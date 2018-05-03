package com.revature.dao;

import java.util.List;

import com.revature.employee.Employee;

public interface EmployeeDao {

	Employee getEmployee();
	List<Employee> getAllEmployees();
	String getPasswordHash();
	boolean insertUser(Employee employee);
	boolean modifyUser();
}
