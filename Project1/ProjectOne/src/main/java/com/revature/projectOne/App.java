package com.revature.projectOne;

import java.util.ArrayList;

import com.revature.dao.Employee;
import com.revature.dao.EmployeeService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	ArrayList<Employee> eList = EmployeeService.getAllEmployee();
    	System.out.println(eList.get(0));
        //Employee e = EmployeeService.;
        //System.out.println(e);
    }
}
