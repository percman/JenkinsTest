package com.revature.dao;

import java.util.List;

import com.revature.model.Admin;



public interface AdminDao {

	public abstract List<Admin> getAllAdmins();
	public abstract Admin getAdmin(String username);
	public abstract boolean insertAdmin(Admin admin);
	public abstract boolean updateAdmin(Admin admin);
	public abstract String getPasswordHash(Admin admin);
}
