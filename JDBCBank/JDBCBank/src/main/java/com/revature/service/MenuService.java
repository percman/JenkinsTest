package com.revature.service;

import com.revature.dao.MenuDao;
import com.revature.dao.MenuDaoImpl;

public class MenuService {

	private static MenuDao dao = MenuDaoImpl.getInstance();
	
	private MenuService() {}
	
	public static boolean usernameTaken(String newUsername) {
		return dao.usernameTaken(newUsername);
	}
	
	public static boolean insertUsername(String username, String type) {
		return dao.insertUsername(username, type);
	}
	
	public static int principalExists() {
		return dao.principalExists();
	}
	
	public static String getType(String username) {
		return dao.getType(username);
	}

}
