package com.revature.dao;

import com.revature.model.Employee;

public interface EmployeeDao {

	Employee getEmployee(String username);
	boolean insertEmployee(Employee employee);
	boolean deleteEmployee(String username);
	String getPasswordHash(Employee employee);

}
