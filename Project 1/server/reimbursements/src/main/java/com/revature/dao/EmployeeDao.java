package com.revature.dao;

import com.revature.model.Employee;

import java.util.List;

public interface EmployeeDao {
    Employee getEmployee(String username);
    String hashPassword(String username, String password);
    String getStoredHash(String username);
    boolean updateEmployeeInfo(Employee e);
    boolean isManager(int id);
    List<String> getEmployeeNames();
}