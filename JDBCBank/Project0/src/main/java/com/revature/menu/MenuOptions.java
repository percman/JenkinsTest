package com.revature.menu;

import static com.revature.menu.MenuAddons.createUser;
import static com.revature.menu.Menus.welcomeScreen;
import static com.revature.readwrite.ReadWrite.inputLine;
import static com.revature.service.UserService.getAllUsers;
import static com.revature.service.UserService.getUser;
import static com.revature.service.UserService.getUserTime;
import static com.revature.service.UserService.updateUser;
import static com.revature.menu.Menus.bankMenu;
import static com.revature.menu.Menus.mainMenu;



import java.util.List;

import static com.revature.menu.Menus.loginAttempt;



import com.revature.exceptions.ChoiceInputException;
import com.revature.exceptions.InvalidBalanceException;
import com.revature.logstatus.LogHere;
import com.revature.users.User;

public class MenuOptions {

	public static User getWelcomeMenuOptions(int choice, User currentUser) {
		
		
		switch(choice) {
		
			case 0: choice = 0;
				System.out.println("Thank you for testing out Adam Lahey's Project Zero!");
				System.out.println("Later!");
				System.exit(0);
			break;
			
			case 1: choice = 1;
			return currentUser = loginAttempt();
			
			case 2: choice = 2;
				createUser();
			return currentUser = welcomeScreen();
			
			default:
		        try {
					throw new ChoiceInputException();
				} catch (ChoiceInputException cie) {
					LogHere.warn(cie.getMessage());
				}
		        return currentUser = welcomeScreen();
		}
		
		return currentUser;
	}
	
	public static User getAdminMenuOptions(int choice, User currentUser) {
		
		switch(choice) {
			
			case 0: choice = 0;
				System.out.println("Thank you for testing out Adam Lahey's Project Zero!");
				System.out.println("Later!");
				System.exit(0);
			
			case 1: choice = 1;
				bankMenu(currentUser);
			break;
			
	        case 2: choice = 2;        
				currentUser = loginAttempt();
			break;
	        
	        case 3: choice = 3;
				createUser();
	        break;
			
	        case 4: choice = 4;
				boolean approved;
				System.out.println("Which account would you like to approve or reject? Enter a username: ");
				String userApproval = inputLine();
				User approvedUser = getUser(userApproval);
				
				System.out.println("approve or reject?");
				String approvalStatus = inputLine();
				if(approvalStatus == "approve")
					approved = true;
				else
					approved = false;
				try {
					approvedUser.setLocked(approved);
					updateUser(approvedUser);
				} catch(NullPointerException npe) {
					System.out.println("Invalid user!");
					LogHere.warn(npe.getMessage());
				}
			break;
			
	        case 5: choice = 5;
	        	boolean locked;
				System.out.println("Which account would you like to lock or unlock? Enter a username: ");
				String userLocked = inputLine();
				User lockedUser = getUser(userLocked);
				System.out.println("lock or unlock?");
				String lockStatus = inputLine();			
				if(lockStatus == "unlock")
					locked = false;
				else
					locked = true;
				try {
					lockedUser.setLocked(locked);
					updateUser(lockedUser);
				} catch(NullPointerException npe) {
					System.out.println("Invalid user!");
					LogHere.warn(npe.getMessage());
				}
	        break;	     
	        
	        case 6: choice = 6;
				System.out.println("Which account would you like to upgrade? Enter a username: ");
				String upgraded = inputLine();
				User upgradeUser = getUser(upgraded);
				upgradeUser.setAdminstatus(true);
				try {
					updateUser(upgradeUser);
				} catch(NullPointerException npe) {
					System.out.println("Invalid user!");
					LogHere.warn(npe.getMessage());
				}
			break;
			
	        case 7: choice = 7;
	        	System.out.println("Which user would you like to see the time of creation? Enter a username");
	        	String usertimed = inputLine();
	        	User userTime = getUser(usertimed);
				try {
		        	System.out.println(getUserTime(userTime));
				} catch(NullPointerException npe) {
					System.out.println("Invalid user!");
					LogHere.warn(npe.getMessage());
				}
	        break;
	        
	        case 8: choice = 8;
	        	List<User> userList = getAllUsers();
	        	for(User u : userList)
	        		System.out.println(u);
	        	
	    	break;
	        
	        default:
		        try {
					throw new ChoiceInputException();
				} catch (ChoiceInputException cie) {
					LogHere.warn(cie.getMessage());
				}
	        
		}
		return currentUser;
	}
	
	public static User getUserMenuOptions(int choice, User currentUser) {
		
		switch(choice) {
			
			case 0: choice = 0;
				System.out.println("Thank you for testing out Adam Lahey's Project Zero!");
				System.out.println("Later!");
				System.exit(0);
			
			case 1: choice = 1;
				bankMenu(currentUser);
			break;
			
	        case 2: choice = 2;        
				currentUser = loginAttempt();
			break;
	        
	        case 3: choice = 3;
				createUser();
	        break;
	        
	        default:
		        try {
					throw new ChoiceInputException();
				} catch (ChoiceInputException cie) {
					LogHere.warn(cie.getMessage());
				}
		}
		return currentUser;
	}

	public static void getBankMenuOptions(int choice, User currentUser) {
		
		switch(choice) {
			
			case 0: choice = 0;
				System.out.println("Back to Main Menu");
				mainMenu(currentUser);
			break;
			
			case 1: choice = 1;
				System.out.println("The current balance is: " + currentUser.getBalance());
			break;
			
			case 2: choice = 2;
				System.out.println("Please enter the deposit amount: ");
				double deposit = 0;
				try {
					String depositLine = inputLine();
					Double depositObject = new Double (depositLine);
					deposit = depositObject.doubleValue();		
				} catch (NumberFormatException nfe) {
					System.out.println("That's not even a number!");
					LogHere.warn(nfe.getMessage());
				}
				currentUser.setBalance(currentUser.getBalance() + deposit);			
				System.out.println("The new balance is: " + currentUser.getBalance());
				updateUser(currentUser);
			break;  
			
	      case 3: choice = 3;
		        System.out.println("Please enter the withdrawn amount: ");
				double withdrawn = 0;
				try {
					String withdrawnLine = inputLine();
					Double withdrawnObject = new Double (withdrawnLine);
					withdrawn = withdrawnObject.doubleValue();	
					if(currentUser.getBalance() - withdrawn < 0) {
						if(currentUser.isAdminstatus())
						{
							currentUser.setBalance(currentUser.getBalance() - withdrawn);			
							System.out.println("The new balance is: " + currentUser.getBalance());
							updateUser(currentUser);
						}
						throw new InvalidBalanceException();	
					}
					else {
						currentUser.setBalance(currentUser.getBalance() - withdrawn);			
						System.out.println("The new balance is: " + currentUser.getBalance());
						updateUser(currentUser);
					}
				} catch (NumberFormatException nfe) {
					System.out.println("That's not even a number!");
					LogHere.warn(nfe.getMessage());
				} catch (InvalidBalanceException ibe) {
					LogHere.warn(ibe.getMessage());
				}

			break;
			
	        default:
		        try {
					throw new ChoiceInputException();
				} catch (ChoiceInputException cie) {
					LogHere.warn(cie.getMessage());
				}
			}
	}

}
