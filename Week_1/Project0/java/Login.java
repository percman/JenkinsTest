package com.revature.project_0;

import java.io.File;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class Login {
		private static final Logger logger = Logger.getLogger(Login.class);

		//User chooses whether they want to create a new account or sign in
		public static void menu() {
			System.out.println("press C to create an account or L to log in");
			Scanner user = new Scanner(System.in);
			try {
				while(true) {
					String answer = user.nextLine();
					answer = answer.toLowerCase();
					if(answer.equals("c")) {
						createUser();
						break;
					}
					else if(answer.equals("l")) {
						signIn();
						break;
					}
					else {
						System.out.println("please type C or L");
					}
				}
			}catch(NoSuchElementException e) {
				logger.fatal("Scanner is missing!", e);
			}catch(Exception e) {
				logger.warn("Error has occured", e);
			}
			user.close();
		}
		
		/*
		 * This function prompts the user to enter their user name and password, It checks to see whether the user name exists and if it
		 * does it then Deserializes the txt file to if it matches the objects password. 
		 */
		private static void signIn() {
			Scanner scan = new Scanner(System.in);
			while(true) {
				System.out.print("Enter your username: ");
				String name = scan.nextLine();
				System.out.print("Enter your password: ");
				String pword = scan.nextLine();
				name = name.toLowerCase();
				if(uNameExist(name)==false)
					System.out.println("That User name does not exist or password is incorrect");
				else {
					File file = new File("src/main/resources/"+name+".txt");
					Account user = SerializationOfNewAccount.deserializeAccount(file);
					if(!user.getPassword().equals(pword)) {
						System.out.println("user name or password is incorrect");
					}
					else {
						/*
						 * Checks to see if the de-serialized obj is an admin or not. 
						 * If they are an admin then they get taken to the admin menu
						 */
						if(UserOrAdmin(user) == true) {
							Admin cast = (Admin)user;
							cast.adminMenu();
							break;
						}
						else {
							/*
							 * In case if the user's account is locked, they are prompted with this message and are prevented from accessing
							 * their account
							 */
							User cast = (User)user;
							if(cast.isLocked()) {
								System.out.println("YOUR ACCOUNT HAS BEEN LOCKED. PLEASE CONTACT ADMIN FOR FURTHER DETAILS");
								menu();
								break;
							}
							//If the user's account is not locked then they are allowed to access their account normally
							else {
								cast.UserHome();
							}
							break;
						}	
					}
				}
					
			}
			scan.close();
		}
		
		//Returns whether or not the user is an admin
		private static boolean UserOrAdmin(Account account) {
			return account.isAdmin();
		}
		
		/*
		 * This method allows new users to set up their account for the first time.
		 * They will need to input their user name, password and amount of money they will deposit
		 */
		private static void createUser() {
			boolean nameAvailability = true;
			boolean pwordVerify = true;
			String name = null;
			String pword = null;
			int money;
			Scanner scan = new Scanner(System.in);
			//User wont be allowed to enter the desired user name if it already exists
			try {
				while(nameAvailability) {
					System.out.print("Please enter your username: ");
					name = scan.nextLine();
					name = name.toLowerCase();
					if (uNameExist(name) == true)
						System.out.println("That user name already exists");
					else
						nameAvailability = false;
				}
			//User puts in their password twice 
			while(pwordVerify) {
				System.out.print("Enter your password: ");
				pword = scan.nextLine();
				System.out.print("Please re-enter your password for confirmation: ");
				String pwordV = scan.nextLine();
				if(pword.equals(pwordV))
					pwordVerify = false;
				else
					System.out.println("You passwords did not match, try again");
			}
			
			System.out.print("Enter the amount of dollars you wish to deposit: $");
			money = scan.nextInt();
			
			//The new user is then serialized and put into a list of users waiting for approval
			User newUser = new User(name,pword,money);
			
			pending(newUser);
			}catch(NoSuchElementException e) {
				logger.fatal("There is no Scanner!", e);
			}catch(Exception e) {
				logger.warn("That file does not exists", e);
			}finally {
				try {
					menu();
					scan.close();
				}catch(NoSuchElementException e) {
					logger.fatal(e.getMessage());
				}
			}
		}
		//The list that serializes users and puts them in a list
		private static void pending(User user) {
			UserList pending = SerializationOfNewAccount.deserializeList();
			if(pending.waitingContains(user)) {
				System.out.println("There is already an account pending with that name, please enter a different name");
			}
			else {
				pending.waitingAdd(user);
				SerializationOfNewAccount.SerializeList(pending);
				System.out.println("Account successfully created, please wait for admin's approval");
	
			}
		}
		//checks to see if a name already exists
		private static boolean uNameExist(String name) {
			File check = new File("src/main/resources/"+name+".txt");
			return check.exists();
		}
		
		
	
}
