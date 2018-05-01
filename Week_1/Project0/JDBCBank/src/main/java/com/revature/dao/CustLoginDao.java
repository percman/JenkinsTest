package com.revature.dao;

import java.util.List;

import com.revature.model.CustLogin;

public interface CustLoginDao {
	
	List<CustLoginDao> getAllCustLoginDao();
	CustLoginDao getUserName(String username);
	CustLoginDao getPassword(String password);
	public abstract String getPasswordHash(CustLogin user);
}