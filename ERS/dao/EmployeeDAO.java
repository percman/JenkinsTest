package com.revature.dao;

import com.revature.model.Employee;
//DAO for the employee table
public interface EmployeeDAO {
	Employee getEmployee(String username);
	String getPasswordHash(Employee e);
}
