package com.revature.dao;

import java.util.List;

import com.revature.employee.Employee;
import com.revature.employee.GenericEmployee;
import com.revature.exceptions.EmployeeNotFoundException;

public interface EmployeeDao {

	public boolean addEmployee(GenericEmployee emp);

	public List<GenericEmployee> getEmployees();

	public GenericEmployee getEmployee(String emp) throws EmployeeNotFoundException;
	
	public boolean updateInfo(int id,String fName,String lName, String email, String add) throws EmployeeNotFoundException;

	String getPasswordHash(GenericEmployee emp);
}
