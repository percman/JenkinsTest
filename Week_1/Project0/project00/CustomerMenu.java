package com.revature.project00;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Scanner;
import org.apache.log4j.Logger;

public class CustomerMenu {
	

	public void Action(CurrentUser) {
		System.out.println("International Bank of David");
		System.out.println("****************************");
		System.out.println("Welcome! Please make a selection:");
		System.out.println("1.Withdraw");
		System.out.println("2.Deposit");
		System.out.println("3.Check Balance");
		System.out.println("4.Quit");
		
		
		Scanner scan = new Scanner(System.in);
		String select = scan.nextLine();
		
		switch (select) {
		case "1":
			System.out.println("Enter Withdrawal Amount:");
			Double w = scan.nextDouble();
			Customer.withdraw(w);
			System.out.println("Your new balance is: " + Customer.getBalance());
			
			
			
			break;
		case "2":
			System.out.println("Enter Deposit Amount: ");
			Double d = scan.nextDouble();
			Customer.deposit(d);
			System.out.println("Your new balance is: " + Customer.getBalance()); 
			
			
			break;
		case "3":
			System.out.println("Your Current Balance is");
			
				FileInputStream fileInputStream = new FileInputStream("src//main//resources//CustomerList.txt");
				BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
				ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);
				Object object = objectInputStream.readObject();
				objectInputStream.close();
				System.out.println(object); 
			}
			
			
			break;
		case "4":
			System.out.println("Thank you, GoodBye!");
			break;
			
		}
		scan.close();	
	}

}
