package com.revature.app;

import java.util.ArrayList;

import com.revature.dao.Employee;
import com.revature.dao.EmployeeService;
import com.revature.reimbursement.Reimbursement;

public class App {
	
    public static void main( String[] args ) throws ClassNotFoundException {
    	Employee e = EmployeeService.getEmployee("jimmy");
    	ArrayList<Reimbursement> rList = EmployeeService.getMyRequests(e);
		for (Reimbursement r: rList) {
			System.out.println(r);
		}
//    	Employee e =  new Employee("Orestes", "T","Brownson", "orestes","password", false);
//    	Employee e2 =  new Employee("John", "H","Newman", "newman","password", true);
//    	EmployeeService.insertEmployee(e);
//    	EmployeeService.insertEmployee(e2);
    	
    	
    }
}
