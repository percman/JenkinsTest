package com.revature.dao;
import java.util.List;
import com.revature.model.Employee;

public interface EmployeeDao {
	
	List<Employee> getAllEmployees();
	Employee getEmployee(String employeeName);
	public boolean insertEmployee(Employee employee);
	public abstract boolean updateEmployee(Employee employee);
	public abstract boolean deleteEmployee(String employeeName);
	
	

/*	List<User> getAllUsers();
	User getUser(String username);
	public boolean insertUser(User user);
	public abstract boolean updateUser(User user);
	public abstract boolean deleteUser(String username);
	public abstract String getPasswordHash(User user);*/
}
