package dao;

import java.util.List;

import model.Employee;

public interface EmployeeDao {
	public abstract boolean createEmployee(String inUsername, String inPassword);
	public abstract Employee readEmployee(String inUsername);
	public abstract List<Employee> readEmployees();
	public abstract boolean updateEmployee(String inManagerUsername, String inUsername, String inPassword);
	public abstract boolean deleteEmployee(String inUsername);
	public abstract boolean authenticateEmployee(String inUsername, String inPassword);
}
