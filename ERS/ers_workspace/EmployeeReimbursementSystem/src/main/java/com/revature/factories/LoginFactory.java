package com.revature.factories;

import com.revature.logging.InvalidLoginException;
import com.revature.logging.LogThis;
import com.revature.model.Employee;
import com.revature.model.FinancialManager;
import com.revature.service.EmployeeService;
import com.revature.service.FinancialManagerService;

public class LoginFactory {

	public static Employee userLogin(Employee employee) throws InvalidLoginException {
//		LogThis.info("do i get here 1");
		boolean isFinMan = EmployeeService.isFinMan(employee);
//		LogThis.info("do i get here 5");

		if (isFinMan) {

			return FinancialManagerService.login(new FinancialManager(employee.getUsername(), employee.getPassword()));
		} else {
			return EmployeeService.login(employee);
		}

	}
}
