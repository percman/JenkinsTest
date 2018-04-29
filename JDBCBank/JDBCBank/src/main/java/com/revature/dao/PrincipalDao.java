package com.revature.dao;

import java.util.List;

import com.revature.users.Person;
import com.revature.users.Principal;
import com.revature.users.Teacher;

public interface PrincipalDao {

	Principal getPrincipal(String username);
	boolean insertPrincipal(Principal principal);
	boolean updatePrincipal(Principal principal);
	String getPasswordHash(Principal principal);

	List<Teacher> getAllTeachers();
	List<Teacher> getUnapprovedTeachers();
	List<Teacher> getUnlockedTeachers();
	List<Teacher> getLockedTeachers();
	boolean approveAllTeachers();
	boolean approveTeacher(String username);
	boolean lockTeacher(String username);
	boolean unlockTeacher(String username);
	boolean deleteTeacher(String username);

}
