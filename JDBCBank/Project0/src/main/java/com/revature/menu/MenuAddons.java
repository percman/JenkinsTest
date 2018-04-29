package com.revature.menu;

import static com.revature.readwrite.ReadWrite.inputLine;

import com.revature.exceptions.InvalidBalanceException;
import com.revature.logstatus.LogHere;
import com.revature.service.UserService;
import com.revature.users.User;

public class MenuAddons {

	public static void createUser() {
		System.out.println("Please enter the username: ");
		String username = inputLine();
		System.out.println("Please enter your password, and I promise not to store in plain text:");
		String password = inputLine();
		System.out.println("If you would like to enter a starting balance, please do so now.");
		
		double startingbalance = 0;
		
		try {
			String balancestring = inputLine();
			Double doubleObject = new Double(balancestring);
			startingbalance = doubleObject.doubleValue();
			if(startingbalance < 0)
				throw new InvalidBalanceException();
			else {
				System.out.println("Please wait while the connection is processed");
				
				User newuser = new User(username, password, startingbalance);
				
				if(UserService.insertUser(newuser)) {
					System.out.println(newuser.getUsername() + " was created successfully.");
					System.out.println("Note: all new users have to be approved by an admin.");
				}
				else {
					System.out.println("User creation failed.");
					System.out.println("Please choose another name.");
				}
			}
		} catch(NumberFormatException nfe) {
			System.out.println("You have entered an invalid balance.");
			LogHere.warn(nfe.getMessage());
		} catch (InvalidBalanceException ibe) {
			System.out.println("You have entered a negative balance.");
			LogHere.warn(ibe.getMessage());
		}
		
		
		
		
	}
	
	public static void createAdmin() {
		System.out.println("Please enter the username: ");
		String username = inputLine();
		System.out.println("Please enter your password, and I promise not to store in plain text:");
		String password = inputLine();
		System.out.println("If you would like to enter a starting balance, please do so now.");
		
		double startingbalance = 0;
		
		try {
			String balancestring = inputLine();
			Double doubleObject = new Double(balancestring);
			startingbalance = doubleObject.doubleValue();
			if(startingbalance < 0)
				throw new InvalidBalanceException();
		} catch(NumberFormatException nfe) {
			System.out.println("You have entered an invalid balance.");
			LogHere.warn(nfe.getMessage());
		} catch (InvalidBalanceException ibe) {
			System.out.println("You have entered a negative balance.");
			LogHere.warn(ibe.getMessage());
		}
		
		System.out.println("Please wait while the connection is processed");
		
		User newuser = new User(username, password, startingbalance);
		
		System.out.println("Was the user created successfully? " + UserService.insertAdmin(newuser));
		System.out.println("Note: all new users have to be approved by an admin.");
		
	}

}
