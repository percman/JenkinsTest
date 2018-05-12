package com.revature.service;

import java.util.List;

import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImpl;
import com.revature.logging.InvalidLoginException;
import com.revature.logging.LogThis;
import com.revature.model.Employee;
import com.revature.model.Reimbursement;

public class EmployeeService {

	private static EmployeeDao dao = EmployeeDaoImpl.getInstance();

	private EmployeeService() {
	}

	public static boolean isFinMan(String username) {
		return dao.isFinMan(username);
	}

	public static Employee login(Employee employee) throws InvalidLoginException {
		Employee temp = dao.getEmployee(employee.getUsername());
		if (dao.getEmployee(employee.getUsername()) == null) {
			throw new InvalidLoginException();
		}
		if (temp.getPassword().equals(dao.getPasswordHash(employee))) {
			LogThis.info(temp.getFirstname() + " " + temp.getLastname() + ", username: " + temp.getUsername()
					+ " has logged in.");
			return temp;
		}
		throw new InvalidLoginException();
	}

	public static Employee getEmployee(String username) {
		return dao.getEmployee(username);
	}

	public static boolean updateEmployee(Employee employee) {
		return dao.updateEmployee(employee);
	}

	public static List<Reimbursement> viewAllReimbursements(Employee employee) {
		return dao.viewAllReimbursements(employee);
	}

	public static List<Reimbursement> viewReimbursementByStatus(Employee employee, int status) {
		return dao.viewReimbursementByStatus(employee, status);
	}

}
