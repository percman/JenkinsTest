package com.revature.dao;

import java.util.ArrayList;

public interface EmployeeDao {
	public Employee getEmployee(int id);
	public ArrayList<Employee> getAllEmployees();
	public boolean insertEmployee(Employee e);
	public boolean updateEmployee(Employee e);
	public boolean deleteEmployee(int id);

}
