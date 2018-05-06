package com.revature.service;

import com.revature.dao.LoginDao;
import com.revature.dao.LoginDaoImpl;
import com.revature.model.Employee;

public class LoginService {

	
	private LoginService() {}
	private static LoginDao dao = LoginDaoImpl.getInstance();
	
	String getPassword(Employee employee) {
		return dao.getPassword(employee);
	}
	String getPasswordHash(Employee employee) {
		return dao.getPasswordHash(employee);
	}
}
