package com.revature.dao;

import com.revature.model.Employee;

public interface EmployeeDao {
    boolean isEmployee(String username);
    Employee getEmployee(String username);
    String hashPassword(String username, String password);
    String getStoredHash(String username);
}