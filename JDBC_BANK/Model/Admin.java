package com.revature.model;

import java.io.Serializable;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.log4j.Logger;

import com.revature.application.Login;
import com.revature.service.AccountAccessService;
import com.revature.service.CredentialsService;

public class Admin extends Account implements Serializable, Menu {
	private static final Logger logger = Logger.getLogger(Admin.class);
	private static final long serialVersionUID = -8873377322849957051L;
	
	public Admin() {}
	
	public Admin(String username,String password) {
		super(username,password);
	}
	/*
	 * This functions prompts the admin to chose between several actions
	 * Can view pending users waiting for their approval
	 * Lock or unlock an existing account
	 * or sign out, which will allow someone else to log in or create an account
	 */
	public void Home(Menu user) {
		System.out.println("Press V to view pending users\nPress L to lock or unlock an existing account\nPress S to sign out");
		try {
			String input = Login.scan.nextLine();
			
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
			logger.warn("There is no Scanner", e);
		}catch(Exception e) {
			logger.warn("An error has occured", e);
		}
	}
	
	private static void BackToMenu() {
		System.out.println("\nPress V to view pending users\nPress L to lock or unlock an existing account\nPress S to sign out");
		try {
			String input = Login.scan.nextLine();
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
			logger.warn("There is no Scanner!",e);
		}catch(Exception e) {
			logger.warn("An error has occured!",e);
		}
	}
	/*
	 * Admin can lock or unlock any account (not including their own)
	 */
	private static void LockUnLockMenu() {
		String chosenOne = null;
		System.out.println("Do you wish to see the locked accounts or unlocked accounts, L/U");
		try {
			while(true) {
				chosenOne = Login.scan.nextLine();
				chosenOne = chosenOne.toLowerCase();
				switch(chosenOne) {
					case "l":
						Lock();
						break;
					case "u":
						UnLock();
						break;
					default:
						BackToMenu();
						break;
				}
			}
		}catch(NoSuchElementException e) {
			logger.warn("There is no Scanner", e);
		}catch(Exception e) {
			logger.warn("An error has occured", e);
		}
		
	}
	
		//Locks a users account
		private static void Lock() {
			List<User> locked = AccountAccessService.getLockedUsers();
			int index = 0;
			if(locked.isEmpty()) 
				System.out.println("There are no locked users");
			else {
				while(index < locked.size()) {
					System.out.println("Do you wish to unlock user "+locked.get(index).getUserName()+" Y/N");
					User user = locked.get(index);
					String answer = Login.scan.nextLine();
					answer.toLowerCase();
					switch(answer) {
						case "y":
							AccountAccessService.Unlock(user);
							locked.remove(index);
							break;
						case "n":
							index++;
							break;
						default:
							System.out.println("User will remain locked");
							index++;
							break;
					}
				}
			}BackToMenu();
		}
		
		//Unlocks a users account
		private static void UnLock() {
			List<User> unlocked = AccountAccessService.getUnlockedUsers();
			int index = 0;
			if(unlocked.isEmpty()) 
				System.out.println("There are no unlocked users");
			else {
				while(index < unlocked.size()) {
					System.out.println("Do you wish to lock user "+unlocked.get(index).getUserName()+" Y/N");
					User user = unlocked.get(index);
					String answer = Login.scan.nextLine();
					answer.toLowerCase();
					switch(answer) {
						case "y":
							AccountAccessService.Lock(user);
							unlocked.remove(index);
							break;
						case "n":
							index++;
							break;
						default:
							System.out.println("User will remain locked");
							index++;
							break;
					}
				}
			}BackToMenu();
		}
	
	/*
	 * If the user is approved, They will be serialized.
	 * If not then they will be deleted from the list of pending users.
	 * Once a user is approved, they will be added to a list of current users.
	 */
	private static void ApproveOrReject() {
		List<User> pending = AccountAccessService.getPendingUsers();
		if(pending.isEmpty()) {
			System.out.println("You have no accounts waiting for approval");
			BackToMenu();
		}
		//Scanner input = new Scanner(System.in);
		int i = 0;
		try {
			while( i < pending.size()) {
				System.out.println("Do you wish to approve "+pending.get(i).getUserName()+"? Y/N");
				User user = pending.get(i);
				String answer = Login.scan.nextLine();
				answer.toLowerCase();
				switch(answer) {
					case "y" :
						AccountAccessService.Approve(user);
						pending.remove(i);
						break;
					case "n" :
						CredentialsService.deleteUser(user.getUserName());
						pending.remove(i);
						break;
					default :
						System.out.println("Skipping user for now");
						i++;
						break;
				}
			}
		}catch(NoSuchElementException e) {
			logger.warn("There is no Scanner", e);
		}catch(Exception e) {
			logger.warn("An error has occured");
		}finally {
				BackToMenu();
				//BackToMenu();
			}
	}
}
