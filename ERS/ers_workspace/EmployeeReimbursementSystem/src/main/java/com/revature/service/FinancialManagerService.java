package com.revature.service;

import java.util.List;

import com.revature.dao.FinancialManagerDao;
import com.revature.dao.FinancialManagerDaoImpl;
import com.revature.logging.InvalidLoginException;
import com.revature.logging.LogThis;
import com.revature.model.Employee;
import com.revature.model.FinancialManager;
import com.revature.model.Reimbursement;

public class FinancialManagerService {

	private static FinancialManagerDao dao = FinancialManagerDaoImpl.getInstance();

	private FinancialManagerService() {
	}

	public static FinancialManager login(FinancialManager financialManager) throws InvalidLoginException {
		FinancialManager temp = dao.getFinancialManager(financialManager.getUsername());
		if (dao.getFinancialManager(financialManager.getUsername()) == null) {
			throw new InvalidLoginException();
		}
		if (temp.getPassword().equals(dao.getPasswordHash(financialManager))) {
			LogThis.info(temp.getFirstname() + " " + temp.getLastname() + ", username: " + temp.getUsername()
					+ " has logged in.");
			return temp;
		}
		throw new InvalidLoginException();
	}
	
	public static FinancialManager getFinancialManager(String username) {
		return dao.getFinancialManager(username);
	}
	
	public static List<Employee> viewAllEmployees() {
		return dao.viewAllEmployees();
	}
	
	public static List<Reimbursement> viewAllReimbursements() {
		return dao.viewAllReimbursements();
	}
	
	public static List<Reimbursement> viewReimbursementsByEmployee(Employee employee) {
		return dao.viewReimbursementsByEmployee(employee);
	}
	
	public static boolean resolveReimbursement(FinancialManager financialmanager, Reimbursement reimbursement, int status) {
		return dao.resolveReimbursement(financialmanager, reimbursement, status);
	}






}
