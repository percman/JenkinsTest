package com.revature.dao;

import com.revature.model.Employee;

public interface LoginDao {

	String getPassword(Employee employee);
	String getPasswordHash(Employee employee);
}
