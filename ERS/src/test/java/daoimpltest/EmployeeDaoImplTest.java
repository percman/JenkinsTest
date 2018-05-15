//package daoimpltest;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//
//import java.util.List;
//
//import org.apache.log4j.Logger;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import daoimpl.EmployeeDaoImpl;
//import daoimpl.ManagerDaoImpl;
//import model.Employee;
//
//public class EmployeeDaoImplTest {
//	static Logger logger;
//	static EmployeeDaoImpl employeeDao;
//	static ManagerDaoImpl managerDao;
//	
//	@BeforeClass
//	public static void setUpBeforeClass() throws Exception {
//		logger = Logger.getLogger(EmployeeDaoImpl.class);
//		employeeDao = EmployeeDaoImpl.getInstance(logger);
//		managerDao = ManagerDaoImpl.getInstance(logger);	
//	}
//
//	@Test
//	public void testCreateEmployee() {
//		managerDao.createManager("william", "password");
//		boolean result = employeeDao.createEmployee("andrew", "password", "william");
//		assertTrue(result);
//		employeeDao.deleteEmployee("andrew");
//	}
//
//	@Test
//	public void testReadEmployee() {
//		employeeDao.createEmployee("andrew", "password");
//		Employee employee = employeeDao.readEmployee("andrew");
//		employeeDao.deleteEmployee("andrew");
//		assertEquals(employee.getUsername(), "andrew");
//	}
//	
//	@Test
//	public void testReadEmployees() {
//		employeeDao.createEmployee("user1", "password");
//		employeeDao.createEmployee("user2", "password");
//		
//		List<Employee> employees = employeeDao.readEmployees();
//		employeeDao.deleteEmployee("user1");
//		employeeDao.deleteEmployee("user2");
//		assertEquals(employees.get(0).getUsername(), "user1");
//		assertEquals(employees.get(1).getUsername(), "user2");
//		assertEquals(employees.size(), 2);
//	}
//
//	@Test
//	public void testUpdateEmployee() {
//		employeeDao.createEmployee("andrew", "password");
//		managerDao.createManager("andrew");
//		
//		boolean result = employeeDao.updateEmployee("andrew", "andrew", "password");
//		assertTrue(result);
//		
//		managerDao.deleteManager("andrew");
//		employeeDao.deleteEmployee("andrew");
//	}
//
//	@Test
//	public void testDeleteEmployee() {
//		employeeDao.createEmployee("andrew", "password");
//		boolean result = employeeDao.deleteEmployee("andrew");
//		assertTrue(result);
//	}	
//}
