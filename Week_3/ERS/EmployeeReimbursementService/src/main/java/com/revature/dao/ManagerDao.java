package com.revature.dao;

import java.util.List;

import com.revature.employee.FinanceManager;
import com.revature.exceptions.EmployeeNotFoundException;

public interface ManagerDao {

	public boolean addManager(FinanceManager man);

	public List<FinanceManager> getManagers();

	public FinanceManager getManager(String man) throws EmployeeNotFoundException;
	
	public boolean updateInfo(int id,String fName,String lName, String email, String add) throws EmployeeNotFoundException;

	String getPasswordHash(FinanceManager man);

	
}
