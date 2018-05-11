package com.revature.application;

import java.util.List;

import com.revature.daoservice.ReimbursementService;
import com.revature.factory.Reimbursement;

public class Application {

	
	public static void main(String[] args) {
//		Employee someperson = new Employee ("neat", "guy");
//
//		System.out.println(someperson);
//		
//		System.out.println(EmployeeService.login(someperson));
//
//		
//		Employee anotherperson = new Employee ("onemoreperson", "password", false, "one", "more", "someemail", 4190123456L);
//
//		System.out.println(anotherperson);
//		
//		System.out.println(EmployeeService.insertEmployee(anotherperson));
//		
//		System.out.println(EmployeeService.getAllEmployees().toString());
		
		System.out.println(ReimbursementService.getAllReimbursements());
		
		List<Reimbursement> reimbursementlist = ReimbursementService.getAllReimbursements();
		
	}
}
