package com.revature.dao.users;

import java.util.ArrayList;

import com.revature.exceptions.UserNotFoundException;
import com.revature.users.Admin;

public class AdminService {
	private AdminDao dao = new AdminDaoImplementation();
	private static AdminService instance;
	private AdminService(){};
	public static AdminService getInstance() {
		if(instance == null) {
			instance = new AdminService();
		}
		return instance;
	}
	public boolean addAdmin(Admin admin) {
		return dao.addAdmin(admin);
	}
	
	public ArrayList<Admin> getAdmins(){
		return dao.getAdmins();
	}
	public Admin getAdmin(String username) throws UserNotFoundException {
		return dao.getAdmin(username);
	}
	
	
}
