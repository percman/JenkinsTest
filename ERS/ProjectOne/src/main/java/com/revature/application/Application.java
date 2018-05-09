package com.revature.application;

import static com.revature.daoservice.EmployeeService.insertEmployee;

import com.revature.daoservice.EmployeeService;
import com.revature.model.Employee;

public class Application {

	
	public static void main(String[] args) {
		Employee someguy = new Employee ("neat", "guy");

		System.out.println(someguy);
		
//		System.out.println(insertEmployee(someguy));
			
		System.out.println(EmployeeService.login(someguy));
	}
}
