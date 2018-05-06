package dao;

import model.Employee;

public interface EmployeeDao {
	boolean createEmployee(int inManagerId, String inUsername, String inPassword);
	Employee readEmployee(int inEmployeeId);
	boolean updateEmployee(int inEmployeeId, int inManagerId, String inUsername, String inPassword);
	boolean deleteEmployee(int inEmployeeId);
}
	