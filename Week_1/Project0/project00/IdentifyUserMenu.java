package com.revature.project00;
import java.util.Scanner;
import org.apache.log4j.Logger;


public class IdentifyUserMenu {
	
	final static Logger log = Logger.getLogger(LoggingHistory.class);
	
	
	public static void identify() {
		System.out.println("International Bank of David");
		System.out.println("****************************");
		System.out.println("Welcome! Please make a selection:");
		System.out.println("1.New User");
		System.out.println("2.Existing User");
		System.out.println("3.Administrator");
		System.out.println("4.Quit");
		
		
		Scanner scan = new Scanner(System.in);
		String select = scan.nextLine();
		
		switch (select) {
		case "1":
			CustomerList.addCustomer();
			
			
			break;
		case "2":
			System.out.println("Customer login");
			System.out.println("*******************");
			System.out.println("Email Address: ");
			String existingemail = scan.nextLine();
			System.out.println("Password: ");
			String existingpassword = scan.nextLine();
			log.info(Customer.getEmail() + "Logged into Bank");
			
			
			break;
		case "3":
			System.out.println("Administrator login");
			System.out.println("*******************");
			System.out.println("Email Address: ");
			String adminemail = scan.nextLine();
			System.out.println("Password: ");
			String adminpassword = scan.nextLine();
			log.info(Customer.getEmail() + "Admin Logged into Bank");

			
			
			
			break;
		case "4":
			System.out.println("Thank you, GoodBye!");
			break;
			
		}
		scan.close();
		
		
		
		
		
	}
	
	
	}


