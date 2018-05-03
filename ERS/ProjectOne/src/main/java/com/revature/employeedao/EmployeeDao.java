package com.revature.employeedao;

import java.sql.Timestamp;
import java.util.List;

import com.revature.users.Employee;

public interface EmployeeDao {

	List<Employee> getAllEmployees();
	List<Employee> getAllManagers();
	Employee getEmployee(String employeename);
	boolean insertEmployee(Employee employee);
	boolean updateEmployee(Employee employee);
	String getPasswordHash(Employee employee);
	Employee getAnyEmployee();
	
}
