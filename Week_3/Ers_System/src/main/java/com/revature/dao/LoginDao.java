package com.revature.dao;

import com.revature.model.Employee;

public interface LoginDao {
	
	Employee getUser(String employee);			//Interface uses model
	public String getPasswordHash(Employee employee);
	
}
