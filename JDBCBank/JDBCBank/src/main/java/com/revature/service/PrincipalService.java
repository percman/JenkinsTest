package com.revature.service;

import java.util.List;

import com.revature.dao.PrincipalDao;
import com.revature.dao.PrincipalDaoImpl;
import com.revature.exceptions.InvalidLoginException;
import com.revature.users.Principal;
import com.revature.users.Teacher;

public class PrincipalService {
	
	private static PrincipalDao dao = PrincipalDaoImpl.getInstance();
	
	private PrincipalService() {}
	
	public static Principal getPrincipal(String username) {
		return dao.getPrincipal(username);
	}
	
	public static boolean insertPrincipal(Principal principal) {
		return dao.insertPrincipal(principal);
	}
	
	public static boolean updatePrincipal(Principal principal) {
		return dao.updatePrincipal(principal);
	}
	
	public static Principal login(Principal principal) throws InvalidLoginException{
		Principal temp = dao.getPrincipal(principal.getUsername());
		if (dao.getPrincipal(principal.getUsername()) == null) {
			throw new InvalidLoginException();
		}
		if (temp.getPassword().equals(dao.getPasswordHash(principal))) {
			System.out.println("You are a valid user, " + temp.getUsername());
			return temp;
		}
		throw new InvalidLoginException();
	}

	public static List<Teacher> getAllTeachers(){
		return dao.getAllTeachers();
	}
	
	public static List<Teacher> getUnapprovedTeachers(){
		return dao.getUnapprovedTeachers();
	}
	
	public static List<Teacher> getUnlockedTeachers(){
		return dao.getUnlockedTeachers();
	}
	
	public static List<Teacher> getLockedTeachers(){
		return dao.getLockedTeachers();
	}
	
	public static boolean approveAllTeachers() {
		return dao.approveAllTeachers();
	}
	
	public static boolean approveTeacher(String username) {
		return dao.approveTeacher(username);
	}
	
	public static boolean lockTeacher(String username) {
		return dao.lockTeacher(username);
	}
	
	public static boolean unlockAllTeacher() {
		return dao.unlockAllTeacher();
	}
	
	public static boolean unlockTeacher(String username) {
		return dao.unlockTeacher(username);
	}
	
	public static boolean deleteTeacher(String username) {
		return dao.deleteTeacher(username);
	}



}
