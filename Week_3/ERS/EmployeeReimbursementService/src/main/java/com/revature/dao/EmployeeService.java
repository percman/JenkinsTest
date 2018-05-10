package com.revature.dao;

import java.util.List;

import com.revature.employee.GenericEmployee;
import com.revature.exceptions.EmployeeNotFoundException;

public class EmployeeService {
private static EmployeeDao dao = EmployeeDaoImpl.getInstance();

public static  boolean addEmployee(GenericEmployee emp) {
	return dao.addEmployee(emp);
}

public static List<GenericEmployee> getEmployees(){
	return dao.getEmployees();
}

public static GenericEmployee getEmployee(String emp) throws EmployeeNotFoundException{
	return dao.getEmployee(emp);
}

public static boolean updateInfo(int id,String fName,String lName, String email, String add) throws EmployeeNotFoundException{
	return dao.updateInfo(id,fName,lName,email,add);
}

public static String getPasswordHash(GenericEmployee emp) {
	return dao.getPasswordHash(emp);
}
}
