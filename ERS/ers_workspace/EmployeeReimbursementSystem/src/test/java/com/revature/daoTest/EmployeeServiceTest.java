package com.revature.daoTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.revature.logging.InvalidLoginException;
import com.revature.logging.LogThis;
import com.revature.model.Employee;
import com.revature.service.EmployeeService;

public class EmployeeServiceTest {

	
	@Test
	public void testGetEmployee() {
		Employee employee = EmployeeService.getEmployee(new Employee("tabbycat2"));
		System.out.println(employee);
		assertEquals("Employee [id=5, username=tabbycat2, firstname=Minerva, middleInitial=null, "
				+ "lastname=McGonagall, phone=1234567890, email=gryffindor@hogwarts.uk]", employee.toString()); 
	}
	
	@Test
	public void testIsFinManFalse() {
		boolean isFinMan = false;
		Employee employee = new Employee("tabbycat2");
		try {
			isFinMan = EmployeeService.isFinMan(employee);
		} catch (InvalidLoginException ile) {
			LogThis.warn(ile.getMessage());
			fail("testIsFinMan failed");
		}
		System.out.println("tabbycat2 isFinMan " + isFinMan);
		assertFalse(isFinMan);
	}
	@Test
	public void testIsFinManTrue() {
		boolean isFinMan = false;
		Employee employee = new Employee("lemondrops");
		try {
			isFinMan = EmployeeService.isFinMan(employee);
		} catch (InvalidLoginException ile) {
			LogThis.warn(ile.getMessage());
			fail("testIsFinMan failed");
		}
		System.out.println("lemondrops isFinMan " + isFinMan);
		assertTrue(isFinMan);
	}

}
