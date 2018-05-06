package com.revature.service;

import com.revature.dao.InformationDAO;
import com.revature.daoImpl.InformationDaoImpl;
import com.revature.model.Employee;

public class InformationService {
	private static InformationDAO dao = new InformationDaoImpl();
	
	private InformationService() {}
	
	public static Employee getInformation(Employee emp) {
		return dao.getInformation(emp);
	}
	
	public static boolean UpdateInformation(Employee emp) {
		return dao.setInformation(emp);
	}
}
