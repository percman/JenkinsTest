package dao;

import java.util.List;

import model.Employee;

public interface EmployeeDao {
	boolean createEmployee(String inUsername, String inPassword, String inManager);
	Employee readEmployee(String inUsername);
	List<Employee> readEmployees();
	boolean updateEmployee(String inManagerUsername, String inUsername, String inPassword);
	boolean deleteEmployee(String inUsername);
	boolean authenticateEmployee(String inUsername, String inPassword);
}
