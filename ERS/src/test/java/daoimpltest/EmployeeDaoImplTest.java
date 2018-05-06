package daoimpltest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import daoimpl.EmployeeDaoImpl;
import model.Employee;

public class EmployeeDaoImplTest {
	static Logger logger;
	static EmployeeDaoImpl dao;
	static Employee andrew;
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		logger = Logger.getLogger(EmployeeDaoImpl.class);
		dao = new EmployeeDaoImpl(logger);
		andrew = new Employee(1, 0, "ahn", "password");
	}

	@Test
	public void testCreateEmployee() {
		boolean result = dao.createEmployee(0, "andrew", "password");
		assertTrue(result);
	}

	@Test
	public void testReadEmployee() {
		Employee inAndrew = dao.readEmployee(0);
		assertEquals(andrew, inAndrew);
	}

	@Test
	public void testUpdateEmployee() {
		boolean result = dao.updateEmployee(0, 0, "ox", "password");
		assertTrue(result);
	}

	@Test
	public void testDeleteEmployee() {
		boolean result = dao.deleteEmployee(0);
		assertTrue(result);
	}

}
