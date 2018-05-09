package com.revature.application;

import com.revature.model.Employee;
import static com.revature.service.EmployeeService.insertEmployee;

public class Application {

	
	public static void main(String[] args) {
		Employee someguy = new Employee ("neat", "guy", false, "neatname", "guyname", "12343214gmail.com", 1144567890L);

		System.out.println(someguy);
		
		System.out.println(insertEmployee(someguy));
	}
}
