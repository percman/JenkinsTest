package com.revature.project_0;

import static com.revature.scanner.BankScanner.*;
import static com.revature.logger.BankLogger.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import com.revature.jdbc.AccountService;
import com.revature.user.User;

/**
 * This class contains the steps for checking if a first time setup is needed and also
 * for running that first time setup if necessary. Any additional initialization methods
 * in the future will go here.
 * @author Allison
 *
 */

public class Initialization {
	
	public static void initializeData() {
		// Any initializing code goes here to run before any console output
		logger.trace("Initializing...");
		firstTimeSetup();
	}

	private static void firstTimeSetup() {

		// Check for existing admin
		System.out.println("Initializing...");
		if(AccountService.checkForAdmin()) {
			return;
		}
		
		// Set admin information
		User admin = new User();
		System.out.println("====================");
		System.out.println("  FIRST TIME SETUP");
		System.out.println("====================");
		System.out.println("Welcome to the Skip's Banking Interface\n");
		System.out.print("Please enter your first name: ");
		admin.setFirstName(input.nextLine());
		System.out.print("\nPlease enter your last name: ");
		admin.setLastName(input.nextLine());
		System.out.print("\nPlease enter your username: ");
		admin.setUsername(input.nextLine());
		System.out.print("\nPlease enter a password: ");
		admin.setPassword(input.nextLine());

		// Add admin to database and promote him/her
		AccountService.insertUser(admin);
		admin = AccountService.getUser(admin.getUsername());
		AccountService.promoteAdmin(admin.getAccountNumber());
		AccountService.approveUser(admin.getAccountNumber());
		
		System.out.println("\n\n\t\t***ADMINISTRATOR ACCOUNT CREATED***\n\nWhen logging in with these credentials, you will be given"
				+ " special administrator priviledges. First time setup complete.");
		logger.trace("First time setup complete...");
	}
}
