package daoimpltest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import daoimpl.EmployeeDaoImpl;
import daoimpl.ManagerDaoImpl;
import model.Manager;

public class ManagerDaoImplTest {
	static Logger logger;
	static EmployeeDaoImpl employeeDao;
	static ManagerDaoImpl managerDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		logger = Logger.getLogger(ManagerDaoImpl.class);
		employeeDao = EmployeeDaoImpl.getInstance(logger);
		managerDao = ManagerDaoImpl.getInstance(logger);
	}

	@Test
	public void testCreateManager() {
		employeeDao.createEmployee("andrew", "password");
		boolean result = managerDao.createManager("andrew");
		
		assertTrue(result);
		managerDao.deleteManager("andrew");
		employeeDao.deleteEmployee("andrew");
	}

	@Test
	public void testReadManager() {
		employeeDao.createEmployee("andrew", "password");
		managerDao.createManager("andrew");
		Manager andrew = managerDao.readManager("andrew");
		assertEquals(andrew.getUsername(), "andrew");
		managerDao.deleteManager("andrew");
		employeeDao.deleteEmployee("andrew");
	}

	@Test
	public void testDeleteManager() {
		employeeDao.createEmployee("andrew", "password");
		managerDao.createManager("andrew");
		boolean result = managerDao.deleteManager("andrew");
		employeeDao.deleteEmployee("andrew");
		assertTrue(result);
	}

}
