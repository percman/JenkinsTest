package com.revature.application;

import java.util.NoSuchElementException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.exception.InvalidInputException;
import com.revature.model.Account;
import com.revature.model.AccountFactory;
import com.revature.model.Menu;
import com.revature.service.AccountAccessService;
import com.revature.service.AccountService;
import com.revature.service.CredentialsService;

public class Login {
		private static final Logger logger = Logger.getLogger(Login.class);
		public static Scanner scan = new Scanner(System.in);
	
		//User chooses whether they want to create a new account or sign in
		public static void menu() {
			System.out.println("press C to create an account or L to log in");
			try {
				while(true) {
					String answer = scan.nextLine();
					answer = answer.toLowerCase();
					if(answer.equals("c")) {
						createUser();
						break;
					}
					else if(answer.equals("l")) {
						signIn();
						break;
					}
				}
			}catch(NoSuchElementException e) {
				logger.warn("Scanner is missing!", e);
			}catch(Exception e) {
				logger.warn("Error has occured", e);
			}finally {
				try {
					scan.close();
				}catch(NoSuchElementException nse) {
					logger.warn(nse.getMessage());
				}
			}
		}
		
		/*
		 * This function prompts the user to enter their user name and password, It checks to see whether the user name exists and if it
		 * does it then it also checks whether or not the users account is accessible i.e locked or pending admin approval
		 */
		private static void signIn() {
			Account account = null;
		
			try {
				System.out.print("Enter your username: ");
				String name = scan.nextLine().toLowerCase();
				System.out.print("Enter your password: ");
				String pword = scan.nextLine();
				account = CredentialsService.login(new Account(name,pword));	
				if(account == null)
					System.out.println("Invalid username/password");
				else if(AccountAccessService.isAdmin(account)==true) {
					Menu admin = AccountFactory.createAccount(account,true);
					admin.Home(admin);
				}
				else if(AccountAccessService.isLocked(account)==true){
					System.out.println("YOUR ACCOUNT IS LOCKED "+account.getUserName()+", CONTACT ADMIN ASAP");
				}
				else if(AccountAccessService.isPending(account)==true) {
					System.out.println("Your account has not been approved yet, we are sorry for the inconvenience");
				}
				else {
					Menu user = AccountFactory.createAccount(account,false);
					user.Home(user);
				}
					
			}catch(InvalidInputException iie) {
				System.err.println("Invalid input: "+iie.getMessage());
				logger.warn(iie.getMessage());
			}finally {
				menu();
			}
				
		}
				
		/*
		 * This method allows new users to set up their account for the first time.
		 * They will need to input their user name, password and amount of money they will deposit
		 * The account will be in the database however it will be inaccessible until admin has approved it
		 */
		private static void createUser() {
			String name = null;
			String pword = null;
			Double money;
			//User wont be allowed to enter the desired user name if it already exists
			try {
				System.out.print("Please enter your username: ");
				name = scan.nextLine().toLowerCase();
				if(CredentialsService.Availability(name) == true)
					System.out.println("That user name already exists");
				else {
					//User puts in their password twice 
					while(true) {
						System.out.print("Enter your password: ");
						pword = scan.nextLine();
						System.out.print("Please re-enter your password for confirmation: ");
						String pwordV = scan.nextLine();
						if(pword.equals(pwordV))
							break;
						else
							System.out.println("You passwords did not match, try again");
					}
				
					System.out.print("Enter the amount of dollars you wish to deposit: $");
					money = scan.nextDouble();
					String strDouble = String.format("%.2f", money);
					money = Double.valueOf(strDouble);
					Account user = new Account(name,pword,money);
					CredentialsService.insertUser(user);
					AccountAccessService.insertAccountAccess(user);
					AccountService.insertAccount(user);
					System.out.println("Your account has been created. Please wait for Admin's approval");
					
				}
			}catch(NoSuchElementException e) {
				logger.warn("There is no Scanner!", e);
			}catch(Exception e) {
				logger.warn("That file does not exists", e);
			}finally {
					menu();
			}
		}

}
