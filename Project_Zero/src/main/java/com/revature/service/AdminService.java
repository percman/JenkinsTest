package com.revature.service;


import com.revature.model.Admin;
import com.revature.dao.AdminDao;
import com.revature.dao.AdminDaoImpl;



public class AdminService {

	private static AdminDao dao = AdminDaoImpl.getInstance();

	private AdminService() {}

	public static Admin getAdmin(String username) {
		return dao.getAdmin(username);
	}
	
	public static boolean insertAdmin(Admin admin) {
		return dao.insertAdmin(admin);
	}
	
	public static Admin login(Admin admin) {
		Admin temp = dao.getAdmin(admin.getUsername());
		
		if (temp.getPassword().equals(dao.getPasswordHash(admin))) {
			System.out.println("You are a valid user, " + temp.getUsername());
			return temp;
		}
		System.err.println("YOU ARE NOT A VALID USER, " + admin.getUsername());
		return null;
	}
}
