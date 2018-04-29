package com.revature.service;

import java.util.List;

import com.revature.dao.AdminDao;
import com.revature.dao.AdminDaoImpl;
import com.revature.model.Admin;

public class AdminService {

	private static AdminDao dao = AdminDaoImpl.getInstance();
	private AdminService() {}
	
	public static List<String> getUnapproved(){
		return dao.getUnapproved();
	}
	public static List<String> getLocked(){
		return dao.getLocked();
	}
	public static boolean insertAdmin(Admin admin) {
		return dao.insertAdmin(admin);
	}
	public static boolean approvePerson(String username) {
		return dao.approvePerson(username);
	}
	public static boolean lockUser(String username) {
		return dao.lockUser(username);
	}
	public static boolean unlockUser(String username) {
		return dao.unlockUser(username);
	}
	public static String getPassword(String username) {
		return dao.getPassword(username);
	}
	public static boolean emptySet() {
		return dao.emptySet();
	}
	
	public static List<String> getUnlocked(){
		return dao.getUnlocked();
	}
}
