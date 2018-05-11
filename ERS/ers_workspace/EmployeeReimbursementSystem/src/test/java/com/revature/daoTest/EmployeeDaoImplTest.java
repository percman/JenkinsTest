package com.revature.daoTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImpl;
import com.revature.model.Employee;

public class EmployeeDaoImplTest {

	private static EmployeeDao dao = EmployeeDaoImpl.getInstance();

	@Test
	public void test() {
		Employee employee = dao.getEmployee("tabbycat2");
		assertEquals("Employee [id=5, username=tabbycat2, firstname=Minerva, middleInitial=null, "
				+ "lastname=McGonagall, phone=1234567890, email=gryffindor@hogwarts.uk]\r\n", employee.toString()); 
	}

}
