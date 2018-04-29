package com.revature.dao;

import java.util.List;

import com.revature.model.Admin;

public interface AdminDao {

	List<String> getUnapproved();
	List<String> getLocked();
	List<String> getUnlocked();
	boolean insertAdmin(Admin admin);
	boolean approvePerson(String username);
	boolean lockUser(String username);
	boolean unlockUser(String username);
	String getPassword(String username);
	boolean emptySet();
}
