package com.revature.ers;

import com.revature.dao.ManagerService;
import com.revature.employee.FinanceManager;
import com.revature.exceptions.EmployeeNotFoundException;

public class application {
	public static void main(String[] args) throws EmployeeNotFoundException {
		System.out.println(ManagerService.getManager("steve").getPassword());
		System.out.println(ManagerService.getPasswordHash(ManagerService.getManager("steve")));
		
		
	}
}
