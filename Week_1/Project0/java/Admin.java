package com.revature.project_0;

import org.apache.log4j.Logger;
import java.io.File;
import java.io.Serializable;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Admin extends Account implements Serializable {
	private static final Logger logger = Logger.getLogger(Admin.class);
	private static final long serialVersionUID = -8873377322849957051L;
	
	public Admin() {}
	
	public Admin(String name, String pword) {
		super(name,pword,true);
	}
	/*
	 * This functions prompts the admin to chose between several actions
	 * Can view pending users waiting for their approval
	 * Lock or unlock an existing account
	 * or sign out, which will allow someone else to log in or create an account
	 */
	public void adminMenu() {
		Scanner admin = new Scanner(System.in);
		System.out.println("Press V to view pending users\nPress L to lock or unlock an existing account\nPress S to sign out");
		try {
			String input = admin.nextLine();
			switch(input) {
				case "v": ApproveOrReject();
					break;
				case "l": LockUnLockMenu();
					break;
				case "s" : 
					Login.menu();
					break;
			}
		}catch(NoSuchElementException e) {
			logger.fatal("There is no Scanner", e);
		}catch(Exception e) {
			logger.warn("An error has occured", e);
		}finally {
			try {
				admin.close();
			}catch(NoSuchElementException nse) {
				logger.fatal(nse.getMessage());
			}
		}
		
	}
	
	private static void BackToMenu() {
		Scanner admin = new Scanner(System.in);
		System.out.println("\nPress V to view pending users\nPress L to lock or unlock an existing account\nPress S to sign out");
		try {
			String input = admin.nextLine();
			switch(input) {
				case "v":
					ApproveOrReject();
					break;
				case "l":
					LockUnLockMenu();
					break;
				case "s" : 
					Login.menu();
					break;
			}
		}catch(NoSuchElementException e) {
			logger.fatal("There is no Scanner!",e);
		}catch(Exception e) {
			logger.warn("An error has occured!",e);
		}finally {
			try {
				admin.close();
			}catch(NoSuchElementException e) {
				logger.fatal(e.getMessage());
			}
		}
	}
	/*
	 * Admin can lock or unlock any account (not included their own)
	 */
	private static void LockUnLockMenu() {
		String chosenOne = null;
		System.out.println("These are your active users ");
		UserList active = SerializationOfNewAccount.deserializeList();
		active.Show();
		Scanner input = new Scanner(System.in);
		try {
			while(true) {
				System.out.println("Enter the user name of the user you wish to lock/unlock: ");
				chosenOne = input.nextLine();
				chosenOne = chosenOne.toLowerCase();
				if(!active.contains(chosenOne))
					System.out.println("That user name is not active");
				else {
					break;
				}
			}
		}catch(NoSuchElementException e) {
			logger.fatal("There is no Scanner", e);
		}catch(Exception e) {
			logger.warn("An error has occured", e);
		}
		File file = new File("src/main/resources/"+chosenOne+".txt");
		User user = SerializationOfNewAccount.deserializeUser(file);
		try {
			//Admin is asked if they want to lock the accound if isLocked returns true
			if(user.isLocked()== true) {
				System.out.println("This account is locked, do you wish to unlock Y/N? ");
				String response = input.nextLine();
				response = response.toLowerCase();
				
				while(true) {
					if(response.equals("y")) {
						UnLock(user);
						break;
					}
					if(response.equals("n")) {
						System.out.println("User "+user.getUserName()+" will remain locked");
						break;
					}
					System.out.println("That is not a valid response please press either Y or N (input is not case sensitive)");
					response = input.nextLine();
					response = response.toLowerCase();
				}
			}
			else {
				//Asked if they want to unlock the account if isLocked returns false
				System.out.println("This account is unlocked, do you wish to lock this account Y/N? ");
				String response = input.nextLine();
				response = response.toLowerCase();
				while(true) {
					if(response.equals("y")) {
						Lock(user);
						break;
					}
					if(response.equals("n")) {
						System.out.println("User "+user.getUserName()+" will remain un-locked");
						break;
					}
					System.out.println("That is not a valid response please press either Y or N (input is not case sensitive)");
					response = input.nextLine();
					response = response.toLowerCase();
				}
			}
		}catch(NoSuchElementException e) {
			logger.fatal("There is no Scanner", e);
		}catch(Exception e) {
			logger.warn("An error has occured", e);
		}finally {
			try {
				BackToMenu();
				input.close();
			}catch(NoSuchElementException nse) {
				logger.fatal(nse.getMessage());
			}
		}
	}
	
	/*
	 * If the user is approved, They will be serialized.
	 * If not then they will be deleted from the list of pending users.
	 * Once a user is approved, they will be added to a list of current users.
	 */
	private static void ApproveOrReject() {
		UserList pending = SerializationOfNewAccount.deserializeList();
		if(pending.waitingSize()==0) {
			System.out.println("You have no user waiting for approval");
			BackToMenu();
		}
		Scanner input = new Scanner(System.in);
		int i = 0;
		try {
		while(i < pending.waitingSize()) {
			System.out.println("Do you wish to approve "+pending.getWaiting(i).getUserName()+"? Y/N");
			User user = pending.getWaiting(i);
			String answer = input.nextLine();
			answer.toLowerCase();
			switch(answer) {
				case "y" :
					Approved(user);
					pending.waitingDelete(user);
					pending.Add(user.getUserName());
					break;
				case "n" :
					pending.waitingDelete(user);
					break;
				default :
					System.out.println("Skipping user for now");
					i++;
					break;
			}
		}
		
		}catch(NoSuchElementException e) {
			logger.fatal("There is no Scanner", e);
		}catch(Exception e) {
			logger.warn("An error has occured");
		}finally {
			try {
				SerializationOfNewAccount.SerializeList(pending);
				BackToMenu();
				input.close();
			}catch(NoSuchElementException nse) {
				logger.fatal(nse);
			}catch(Exception e) {
				logger.warn(e.getMessage());
			}
		}
	}
	//Serializes approved users
	private static void Approved(User user) {
		SerializationOfNewAccount.SerializeAccount(user, new File("src/main/resources/"+user.getUserName()+".txt"));
	}
	//Locks a users account
	private static void Lock(User user) {
		user.setLocked(true);
		SerializationOfNewAccount.SerializeAccount(user, new File("src/main/resources/"+user.getUserName()+".txt"));
	}
	//Unlocks a users account
	private static void UnLock(User user) {
		user.setLocked(false);
		SerializationOfNewAccount.SerializeAccount(user, new File("src/main/resources/"+user.getUserName()+".txt"));

	}
}
