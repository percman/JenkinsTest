package com.revature.application;

import com.revature.factory.Reimbursement;
import com.revature.reimbursement.FoodReimbursement;
import com.revature.service.ReimbursementService;

public class Application {

	public static void main(String[] args) {
//		Manager m = new Manager();
//		m.setFirstname("Jesse");
//		m.setManager_id(4);
//		m.setId(14);
//		System.out.println(m.toString());
//		
//		Employee employee = new Employee();
//		employee.setUsername("Patrick");
//		employee.setPassword("penguins");
//		employee.setFirstname("Patrick");
//		employee.setLastname("Star");
//		EmployeeService.insertUser(employee);
//		employee.setUsername("Allie2");
//		employee.setPassword("penguins");
//		employee.setFirstname("Allison");
//		employee.setLastname("Skipper");
//		EmployeeService.insertUser(employee);
//		employee.setUsername("Kinsey2");
//		employee.setPassword("penguins");
//		employee.setFirstname("Kinsey");
//		employee.setLastname("Webb");
//		EmployeeService.insertUser(employee);
		
//		Employee employee = new Employee();
//		employee = EmployeeService.getEmployee(2);
//		System.out.println(employee);
		
//		List<Employee> employees = new ArrayList<>();
//		for (Employee employee : EmployeeService.getAllEmployees()) {
//			System.out.println(employee);
//		}
		
//		Reimbursement frb = new FoodReimbursement();
//		frb.setAmount(54);
//		frb.setRequestor_id(49);
//		frb.setMessage("Went to the penguin zoo");
//		ReimbursementService.submitRequest(frb,"src/main/resources/FakeReceipt.jpg");
		
		System.out.println(ReimbursementService.getAllImages());
//		
//		Reimbursement trb = new TravelReimbursement();
//		trb.setAmount(3.98f);
//		trb.setRequestor_id(5);
//		ReimbursementService.submitRequest(trb);
//		
//		for (Reimbursement rb : ReimbursementService.getAllRequests()) {
//			System.out.println(rb);
//		}
		
//		Manager m = new Manager();
//		m.setUsername("Alli");
//		m.setPassword("penguins");
//		m.setFirstname("Allison");
//		m.setLastname("Skipper");
//		ManagerService.insertManager(m);
		
//		System.out.println(ManagerService.isManager(41));
		
//		System.out.println(ManagerService.getManager(1));
		
//		ReimbursementService.approveReimbursement(49, 7, 28);
//		ReimbursementService.denyReimbursement(5, 2, 23);
//		
//		for (Reimbursement rb : ReimbursementService.getAllRequests()) {
//			System.out.println(rb + "\n");
//		}
//		Employee employee = new Employee();
//		employee.setUsername("Skip_a_few");
//		employee.setPassword("penguins");
//		EmployeeService.login(employee);
		
//		System.out.println(EmployeeService.getEmployee("Skip_a_few"));
		
//	System.out.println(ReimbursementService.getRequests(47));
		
//		ReimbursementService.insertPhoto(27);
//		try {
//			InputStream in = ReimbursementService.retrievePhoto(27).getBinaryStream();
//			try {
//				BufferedImage image = ImageIO.read(in);
//				System.out.println(image);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	
		
//		Image image = new Image();
//		image.setBlob(ReimbursementService.retrievePhoto(27));
//		System.out.println(image.getBlobBytes());
		
		
	
	//ReimbursementService.approveReimbursement(requestorId, managerId, reimbursementId)
	}
}
