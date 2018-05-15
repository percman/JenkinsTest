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
//import daoimpl.InformationDaoImpl;
//import daoimpl.ManagerDaoImpl;
//import daoimpl.ReimbursementDaoImpl;
//import model.Reimbursement;
//
//public class ReimbursementDaoImplTest {
//	static Logger logger;
//	static EmployeeDaoImpl employeeDao;
//	static ManagerDaoImpl managerDao;
//	static ReimbursementDaoImpl reimburseDao;
//
//	@BeforeClass
//	public static void setUpBeforeClass() throws Exception {
//		logger = Logger.getLogger(InformationDaoImpl.class);
//		employeeDao = EmployeeDaoImpl.getInstance(logger);
//		managerDao = ManagerDaoImpl.getInstance(logger);
//		reimburseDao = ReimbursementDaoImpl.getInstance(logger);
//	}
//
//	@Test
//	public void testCreateReimbursement() {
//		employeeDao.createEmployee("andrew", "password");
//		managerDao.createManager("andrew");
//		boolean result = reimburseDao.createReimbursement("andrew", "status", "image", "category");
//		
//		reimburseDao.deleteReimbursement("andrew");
//		managerDao.deleteManager("andrew");
//		employeeDao.deleteEmployee("andrew");
//		
//		assertTrue(result);
//
//	}
//
////	@Test
////	public void testReadReimbursement() {
////		employeeDao.createEmployee("andrew", "password");
////		managerDao.createManager("andrew");
////		boolean result = reimburseDao.createReimbursement("andrew", "status", "image", "category");
////		List<Reimbursement> reimbursement = reimburseDao.readReimbursements("andrew");
////		
////		reimburseDao.deleteReimbursement("andrew");
////		managerDao.deleteManager("andrew");
////		employeeDao.deleteEmployee("andrew");
////		
////		assertTrue(result);
////		assertEquals(reimbursement.getStatus(), "status");
////		assertEquals(reimbursement.getImage(), "image");
////		assertEquals(reimbursement.getCategory(), "category");
////
////	}
//	
//	@Test
//	public void testReadReimbursements() {
//		employeeDao.createEmployee("andrew", "password");
//		managerDao.createManager("andrew");
//		reimburseDao.createReimbursement("andrew", "status", "image", "category");
//		reimburseDao.createReimbursement("andrew", "status", "image", "category");
//		List<Reimbursement> reimbursements = reimburseDao.readReimbursements();
//	
//		reimburseDao.deleteReimbursement("andrew");
//		reimburseDao.deleteReimbursement("andrew");
//		managerDao.deleteManager("andrew");
//		employeeDao.deleteEmployee("andrew");
//		
//		assertEquals(2, reimbursements.size());
//		
//
//	}
//
////	@Test
////	public void testUpdateReimbursement() {
////		employeeDao.createEmployee("andrew", "password");
////		managerDao.createManager("andrew");
////		reimburseDao.createReimbursement("andrew", "status", "image", "category");
////		boolean result = reimburseDao.updateReimbursement("andrew", "newStatus", "newImage", "newCategory");
////		Reimbursement reimbursement = reimburseDao.readReimbursement("andrew");
////		
////		reimburseDao.deleteReimbursement("andrew");
////		managerDao.deleteManager("andrew");
////		employeeDao.deleteEmployee("andrew");		
////		
////		assertTrue(result);
////		assertEquals(reimbursement.getStatus(), "newStatus");
////		assertEquals(reimbursement.getImage(), "newImage");
////		assertEquals(reimbursement.getCategory(), "newCategory");
////	}
//
//	@Test
//	public void testDeleteReimbursement() {
//		employeeDao.createEmployee("andrew", "password");
//		managerDao.createManager("andrew");
//		reimburseDao.createReimbursement("andrew", "status", "image", "category");
//		boolean result = reimburseDao.deleteReimbursement("andrew");
//		reimburseDao.deleteReimbursement("andrew");
//		managerDao.deleteManager("andrew");
//		employeeDao.deleteEmployee("andrew");	
//		
//		assertTrue(result);
//		
//
//	}
//}
