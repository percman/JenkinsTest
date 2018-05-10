package com.revature.service;

import java.util.List;

import com.revature.dao.ManagerDao;
import com.revature.dao.ManagerDaoImplementation;
import com.revature.employee.Employee;
import com.revature.manager.Manager;

public class ManagerService {

	private static ManagerDao dao = new ManagerDaoImplementation();

	private ManagerService() {
	}

	public static boolean insertManager(Manager manager) {
		return dao.insertManager(manager);
	}

	public static boolean isManager(int id) {
		return dao.isManager(id);
	}
	
	public static Manager getManager(int id) {
		return dao.getManager(id);
	}

	public static List<Manager> getAllManagers() {
		return dao.getAllManagers();
	}
}
