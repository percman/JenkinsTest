package com.revature.app;

import java.util.ArrayList;

import com.revature.dao.Employee;
import com.revature.dao.EmployeeService;

public class App {
	
    public static void main( String[] args ) {
    	ArrayList<Employee> eList = EmployeeService.getAllEmployees();
    	System.out.println(eList.size());
    	for(Employee e : eList) {
    		System.out.println(e);
    	}
//    	Employee e = new Employee("John", "R", "Tolkien", "JrrTokkien", "hobbit", true);
//    	boolean inserted = EmployeeService.insertEmployee(e);
//    	System.out.println(inserted);
    }
}
