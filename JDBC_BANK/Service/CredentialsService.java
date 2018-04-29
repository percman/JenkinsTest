package com.revature.service;

import com.revature.DAO.CredentialsDAO;
import com.revature.DAO.CredentialsDAOImpl;
import com.revature.model.Account;

public class CredentialsService {
	private static CredentialsDAO dao = new CredentialsDAOImpl();
	
	private CredentialsService() {}
	
	public static boolean Availability(String name) {
		return dao.CheckAvailablility(name);
	}
	
	public static boolean insertUser(Account user) {
		return dao.insertCredentials(user);
	}
	
	public static boolean deleteUser(String username) {
		return dao.deleteCredentials(username);
	}
	
	public static Account login(Account user) {
		Account temp = dao.getCredentials(user.getUserName());
		if(temp.getPassword().equals(dao.getPasswordHash(user))) {
			System.out.println("Welcome, "+temp.getUserName());
			return temp;
		}
		return null;
	}
}
