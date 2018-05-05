package com.revature.dao;

import java.util.ArrayList;

import com.revature.reimbursement.Reimbursement;

public interface EmployeeDao {
	public Employee getEmployee(int id);
	public ArrayList<Employee> getAllEmployees();
	public boolean insertEmployee(Employee e);
	public boolean deleteEmployee(int id);
	public boolean insertRequest(Reimbursement r);
}
