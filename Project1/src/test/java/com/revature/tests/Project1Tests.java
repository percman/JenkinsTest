package com.revature.tests;


import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.model.Employee;
import com.revature.model.Manager;
import com.revature.service.EmployeeService;
import com.revature.service.ManagerService;

public class Project1Tests {

	Employee employee = new Employee("username", "password", "Bob", 'B', "Smith");
	Manager manager = new Manager("Username", "password", "Boss", 'O', "Company");
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEmployeeInsert() {
		assertTrue(EmployeeService.insertEmployee(employee));
	}
	
	@Test
	public void testManagerInsert() {
		assertTrue(ManagerService.insertManager(manager));
	}
	
	@Test
	public void testEmployeeUpdate() {
		employee.setLastName("NewName");
		assertTrue(EmployeeService.updateEmployee(employee));
	}

}
