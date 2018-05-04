package com.revature.projectOne;

import com.revature.dao.EmployeeService;
import com.revature.reimbursement.Reimbursement;

/**
 * Cameron Skaggs
 * Revature Project 1: May 2018
 *
 */
public class App {
    public static void main( String[] args ) {
//    	ArrayList<Employee> eList = EmployeeService.getAllEmployee();
//    	System.out.println(eList.get(0));
//        Employee e = EmployeeService.getEmployee(1);
//        System.out.println(e);
//    	Employee e = new Employee("John", "R", "Tolkien", "beren", "hobbits", true);
//    	System.out.println(EmployeeService.insertEmployee(e));
    	Reimbursement r = new Reimbursement(1, 1, 3, "travel", "pending");
    	System.out.println(EmployeeService.insertRequest(r));
    }
}
