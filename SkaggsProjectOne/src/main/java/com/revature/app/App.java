package com.revature.app;

import java.sql.Blob;
import java.sql.SQLException;

import com.revature.dao.EmployeeService;

public class App {
	
    public static void main( String[] args ) throws ClassNotFoundException {
    	
    	byte[] image = EmployeeService.getImage(15);
    	System.out.println(image);
    }
}
