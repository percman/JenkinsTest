package com.revature.application;

import static com.revature.menu.Menus.*;

import com.revature.users.User;

/**
 * 
 * @author Adalah
 *
 * Wow I can't believe it's all done with!
 * Had a blast writing this. Hope you like it!
 *
 */

public class Application{
	
	public static void main(String[] args) {	
				
//		firstRun();
		
//		loginAttempt();
	
		
		User curruser = welcomeScreen();
		
		mainMenu(curruser);
		
		
		
	}

}