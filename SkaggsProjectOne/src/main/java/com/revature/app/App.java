package com.revature.app;

import java.util.ArrayList;

import com.revature.dao.Employee;
import com.revature.dao.EmployeeService;
import com.revature.reimbursement.Reimbursement;

public class App {
	
    public static void main( String[] args ) throws ClassNotFoundException {

		//    	ArrayList<Employee> eList = EmployeeService.getAllEmployees();
//    	System.out.println(eList.size());
//    	for(Employee e : eList) {
//    		System.out.println(e.getFirstName() + e.getLastName());
////    	}
//    	Employee e = new Employee("Jimmy", "W", "Carter", "jimmy", "password", false);
//    	boolean inserted = EmployeeService.insertEmployee(e);
//    	System.out.println(inserted);
//    	Employee e =  EmployeeService.getEmployee("nixnax");
//    	System.out.println(e.getFirstName());
//    	e.setPassword("Password");
//    	EmployeeService.updateEmployee(e);
//    	Employee e = EmployeeService.getEmployee("ham");
//    	e.setPassword("password2");
//    	boolean update = EmployeeService.updateEmployee(e);
//    	System.out.println(update);
//    	Employee e = EmployeeService.getEmployee("polka");
//    	System.out.println(e + "\n Employee id " + e.getEmployeeId());
		ArrayList<Reimbursement> rList = EmployeeService.getAllRequests();
		for (Reimbursement r: rList) {
			System.out.println(r);
		}
    }
}
