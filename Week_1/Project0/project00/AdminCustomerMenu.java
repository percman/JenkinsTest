package com.revature.project00;

import java.util.Scanner;

public class AdminCustomerMenu {
	
	public static void Modify() {
		System.out.println("International Bank of David");
		System.out.println("****    Admin Only! *******");
		System.out.println("Welcome! Please make a selection:");
		System.out.println("1.Activate Customer");
		System.out.println("2.Authorize user As Administrator");
		System.out.println("3.Update Account Name");
		System.out.println("4.Update Account Last Name");
		System.out.println("5.Update Account Email");
		System.out.println("6.Update Acount Password");
		System.out.println("7.Search for Account");
		
		
		Scanner scan = new Scanner(System.in);
		String select = scan.nextLine();
		
		switch (select) {
		case "1":
			System.out.println("Enter Withdrawal Amount:");
			Double w = scan.nextDouble();
			
			
			
			break;
		case "2":
			System.out.println("Enter Deposit Amount: ");
			Double d = scan.nextDouble();
			
			
			break;
		case "3":
			System.out.println("Your Current Balance is");
			
			
			break;
		case "4":
			System.out.println("Thank you, GoodBye!");
			break;
		case "5":
			System.out.println("Thank you, GoodBye!");
			break;
		case "6":
			System.out.println("Thank you, GoodBye!");
			break;
		case "7":
			System.out.println("Thank you, GoodBye!");
			break;
		
		}
		scan.close();	
	}

}
