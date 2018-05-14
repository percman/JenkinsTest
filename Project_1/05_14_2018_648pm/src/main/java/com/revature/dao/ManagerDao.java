package com.revature.dao;

import java.util.List;

import com.revature.manager.Manager;

public interface ManagerDao {

	Manager getManager(int id);
	List<Manager> getAllManagers();
	boolean insertManager(Manager manager);
	boolean modifyManager();
	boolean isManager(int id);
}
