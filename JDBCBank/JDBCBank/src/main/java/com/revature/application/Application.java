package com.revature.application;



import static com.revature.menu.Menus.firstRun;
import static com.revature.menu.Menus.mainMenu;
import static com.revature.menu.Menus.welcomeScreen;
import static com.revature.service.UserService.getAnyUser;

import java.text.DecimalFormat;
import java.util.List;

import static com.revature.service.UserInterest.generateInterest;

import com.revature.users.User;

/**
 * 
 * @author Adalah
 *
 * 
 *
 */

public class Application{
	
	public static void main(String[] args) {	
		
		DecimalFormat df = new DecimalFormat("0.00");
		List<User> priorToInterest = generateInterest();
		
		if(getAnyUser() == null)
			firstRun();
		User curruser = welcomeScreen();
				
		for(User inList : priorToInterest) {
			if(inList.getUsername().equals(curruser.getUsername())) {
				System.out.println("You have generated $" + df.format(inList.getBalance() - curruser.getBalance()) + " interest on your savings. Congrats!");
			}
		}
		
		mainMenu(curruser);
		
		
	}
	


	
}