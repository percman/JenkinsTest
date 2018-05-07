package com.revature.application;

import com.revature.model.Employee;
import static com.revature.service.EmployeeService.insertEmployee;

public class Application {

	
	public static void main(String[] args) {
		Employee someguy = new Employee ("neatdude", "password", true, "neat", "guy", "123", 4193778203L);

		System.out.println(someguy);
		
		System.out.println(insertEmployee(someguy));
	}
}
