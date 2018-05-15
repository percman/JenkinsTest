package com.revature.dao;

import java.sql.Blob;
import java.util.ArrayList;

import com.revature.reimbursement.Reimbursement;

public interface EmployeeDao {
	public Employee getEmployee(int id) throws ClassNotFoundException;
	public ArrayList<Employee> getAllEmployees() throws ClassNotFoundException;
	public boolean insertEmployee(Employee e) throws ClassNotFoundException;
	public boolean deleteEmployee(int id);
	public boolean insertRequest(Reimbursement r) throws ClassNotFoundException;
	String getPasswordHash(Employee employee);
	Employee getEmployee(String username) throws ClassNotFoundException;
	boolean updateEmployee(Employee e);
	boolean updateEmployeeWithoutPassword(Employee e);
	ArrayList<Reimbursement> getAllRequests() throws ClassNotFoundException;
	boolean updateRequest(Employee e, int reid, String approved);
	ArrayList<Reimbursement> getMyRequests(Employee e) throws ClassNotFoundException;
	byte[] getImage(int reid);
	Reimbursement getRequest(int id) throws ClassNotFoundException;
}
