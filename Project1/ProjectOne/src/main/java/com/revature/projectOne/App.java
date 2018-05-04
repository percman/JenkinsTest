package com.revature.projectOne;

import java.util.ArrayList;

import com.revature.dao.Employee;
import com.revature.dao.EmployeeService;

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
    	Employee e = new Employee("Clive", "S", "Lewis", "Narnia", "aslan");
    	System.out.println(EmployeeService.insertEmployee(e));
    }
}
