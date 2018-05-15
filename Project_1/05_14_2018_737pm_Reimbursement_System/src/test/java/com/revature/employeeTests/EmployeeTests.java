package com.revature.employeeTests;

import com.revature.employee.*;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class EmployeeTests {

	Employee employee = new Employee();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}
	
	@Test
	public void test1() {
		employee.setFirstname("BOB");
		assertTrue(employee.getFirstname().equals("BOB"));
		assertTrue(employee.getFirstname() == "BOB");
	}
	
	@Test
	public void test2() {
		employee.setPassword("Penguins");
		assertTrue(employee.getPassword().equals("Penguins"));
		assertTrue(employee.getPassword() == "Penguins");
	}

}
