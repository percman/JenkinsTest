package com.revature.service;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

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

	public static boolean isFinMan(Employee employee) throws InvalidLoginException {

		boolean isFinMan = dao.isFinMan(employee.getUsername());
		if (dao.getEmployee(employee.getUsername()) == null) {
			throw new InvalidLoginException();
		}

		if (isFinMan) {
			return true;
		} else {
			return false;
		}

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

	public static Employee getEmployee(Employee employee) {
		return dao.getEmployee(employee.getUsername());
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
	
	public static String employeeHome(HttpServletRequest request) {
		return "employeeHome.jsp";
		
	}

}
