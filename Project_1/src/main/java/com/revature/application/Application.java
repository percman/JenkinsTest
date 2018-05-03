package com.revature.application;

import com.revature.employee.Employee;
import com.revature.manager.Manager;
import com.revature.service.EmployeeService;

public class Application {

	public static void main(String[] args) {
		Manager m = new Manager();
		m.setFirstname("Jesse");
		m.setManager_id(4);
		m.setId(14);
		System.out.println(m.toString());
		
		Employee employee = new Employee();
		employee.setUsername("Skip_a_few");
		employee.setPassword("penguins");
		EmployeeService.insertUser(employee);
		employee.setUsername("Allie");
		employee.setPassword("penguins");
		EmployeeService.insertUser(employee);
		employee.setUsername("Kinsey");
		employee.setPassword("penguins");
		EmployeeService.insertUser(employee);
	}
}
