package com.revature.application;

import static com.revature.menu.GUI.*;
import static com.revature.readwrite.ReadWrite.*;
import static com.revature.users.SerializationOfUsers.*;
import static com.revature.users.Admin.*;



import java.util.HashMap;

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
		
		String currentUser = "";
		HashMap<Integer, User> userHashData = new HashMap<>();
		int choice = 0;
		
		
		if(!userFile.exists()) {
			currentUser = firstRun();
			userHashData = hashMapUserData(userFile);
		}
		else
		{
			userHashData = hashMapUserData(userFile);
			currentUser = welcomeScreen(userHashData);
		}
		
		
		mainMenu(userHashData, currentUser);

		codeCleanUp();
	}

}