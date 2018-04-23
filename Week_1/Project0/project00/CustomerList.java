package com.revature.project00;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class CustomerList implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9141746231541128903L;
	
	public static List<Customer> collection = new ArrayList<Customer>();
		

	
	final static Logger log = Logger.getLogger(LoggingHistory.class);
	
	
	public CustomerList() {
		
	
	}
	public static void addCustomer() {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Registration");
		System.out.println("*******************");
		System.out.println("Please enter the following");
		System.out.println("First Name: ");
		String firstN = scan.nextLine();
		System.out.println("Last Name: ");
		String lastN = scan.nextLine();
		System.out.println("Email address: ");
		String email = scan.nextLine();
		System.out.println("Password: ");
		String password = scan.nextLine();
		Customer customer = new Customer(firstN, lastN, email, password);
		System.out.println(customer.toString());
		addCustomer(customer);
		
		
		log.info("New User Added " + customer.getEmail());
		
		
		
		//temp message after registration confirming details to check
		for(Customer c : collection ) {
			System.out.println("Customer found: " + c.getFirstN());
		}
		
	

		System.out.println("Thank you for registering!");
		IdentifyUserMenu.identify();
		
		scan.close();
		
	}
	public static void addCustomer(Customer customer) {
	collection.add(customer);
	
	try {
	
	FileOutputStream fos = new FileOutputStream("src//main//resources//CustomerList.txt");
	ObjectOutputStream oos = new ObjectOutputStream(fos);
	oos.writeObject(collection);
	oos.close();
	fos.close();
	}catch(IOException ioe) {
		ioe.printStackTrace();
	}
	
	
	}

	@Override
	public String toString() {
		String total = "\n";
		
		for (int j=0; j<collection.size(); j++) {
			Customer c = collection.get(j);
			
			total = total + c.toString();
		}
		return total;
		
		
			
		
	}
}
		
	

