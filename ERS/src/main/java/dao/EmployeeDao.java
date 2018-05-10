package dao;

import model.Employee;

public interface EmployeeDao {
	boolean createEmployee(String inUsername, String inPassword);
	Employee readEmployee(String inUsername);
	boolean updateEmployee(String inManagerUsername, String inUsername, String inPassword);
	boolean deleteEmployee(String inUsername);
}
	