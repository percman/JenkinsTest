package com.revature.application;

import static com.revature.menu.Menus.firstRun;
import static com.revature.menu.Menus.mainMenu;
import static com.revature.menu.Menus.welcomeScreen;
import static com.revature.service.UserService.getAllUsers;
import static com.revature.service.UserService.getAnyUser;
import static com.revature.service.UserService.generateUserInterest;


import com.revature.users.User;

/**
 * 
 * @author Adalah
 *
 * Wow I can't believe it's all done with!
 * Had a blast writing this. Hope you like it!
 *
 */

@SuppressWarnings("unused")
public class Application{
	
	public static void main(String[] args) {	
		
		if(getAnyUser() == null)
			firstRun();
		User curruser = welcomeScreen();
				
		mainMenu(curruser);
		
	}

}