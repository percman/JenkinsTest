package service;

import java.util.List;

import org.apache.log4j.Logger;

import daoimpl.EmployeeDaoImpl;
import model.Employee;

public class EmployeeService {
	private static Logger logger = Logger.getLogger(EmployeeService.class);
	private static EmployeeDaoImpl dao = EmployeeDaoImpl.getInstance(logger);
	
	public static boolean createEmployee(String inUsername, String inPassword) {
		return dao.createEmployee(inUsername, inPassword);
	}
	public static Employee readEmployee(String inUsername) {
		return dao.readEmployee(inUsername);
	}
	public static List<Employee> readEmployees() {
		return dao.readEmployees();
	}
	public static boolean updateEmployee(String inManagerUsername, String inUsername, String inPassword) {
		return dao.updateEmployee(inManagerUsername, inUsername, inPassword);
	}
	public static boolean deleteEmployee(String inUsername) {
		return dao.deleteEmployee(inUsername);
	}
}
