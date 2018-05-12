package com.revature.daoTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.revature.model.Employee;
import com.revature.service.EmployeeService;

public class EmployeeServiceTest {


	@Test
	public void test() {
		Employee employee = EmployeeService.getEmployee("tabbycat2");
		assertEquals("Employee [id=5, username=tabbycat2, firstname=Minerva, middleInitial=null, "
				+ "lastname=McGonagall, phone=1234567890, email=gryffindor@hogwarts.uk]\r\n", employee.toString()); 
	}

}
