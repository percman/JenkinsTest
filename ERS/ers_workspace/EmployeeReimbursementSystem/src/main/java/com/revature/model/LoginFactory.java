package com.revature.model;

import com.revature.logging.InvalidLoginException;
import com.revature.logging.LogThis;
import com.revature.service.EmployeeService;
import com.revature.service.FinancialManagerService;

public class LoginFactory {

	public static Employee userLogin(String isFinMan, Employee employee) {
		try {
		if (isFinMan.equals("true")) {
			return FinancialManagerService.login((FinancialManager) employee);
		} else {
			return EmployeeService.login(employee);
		}
		} catch (InvalidLoginException ile) {
			LogThis.warn(ile.getMessage());
		}
		return null;
	}
}
