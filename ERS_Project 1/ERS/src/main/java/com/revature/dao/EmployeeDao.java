package com.revature.dao;

import java.util.List;

import com.revature.model.Employee;

public interface EmployeeDao {

	public abstract List<Employee> getAllEmployees();
	public abstract Employee getEmployee(int employeeId);
	public abstract boolean insertEmployee(Employee employee);
	public abstract boolean updateEmployee(Employee employee);
	public abstract String getPasswordHash(Employee employee);
	
}
