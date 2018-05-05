package com.revature.dao;

import java.util.List;

import com.revature.employee.GenericEmployee;
import com.revature.exceptions.EmployeeNotFoundException;

public class EmployeeService {
private EmployeeDao dao = EmployeeDaoImpl.getInstance();

public boolean addEmployee(GenericEmployee emp) {
	return dao.addEmployee(emp);
}

public List<GenericEmployee> getEmployees(){
	return dao.getEmployees();
}

public GenericEmployee getEmployee(String emp) throws EmployeeNotFoundException{
	return dao.getEmployee(emp);
}

public boolean updateInfo(GenericEmployee emp) throws EmployeeNotFoundException{
	return dao.updateInfo(emp);
}

String getPasswordHash(GenericEmployee emp) {
	return dao.getPasswordHash(emp);
}
}
