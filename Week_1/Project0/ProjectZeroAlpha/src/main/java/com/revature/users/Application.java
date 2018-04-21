package com.revature.users;

import static com.revature.readwrite.ReadWrite.*;
import static com.revature.users.SerializationOfUsers.*;
import static com.revature.users.GUI.*;
import static com.revature.users.Admin.*;



import java.util.HashMap;

@SuppressWarnings("unused")
public class Application{
	
	public static void main(String[] args) {	
		
		userFile.delete();
		
		String currentUser = "";
		HashMap<Integer, User> userHashData = new HashMap<>();
		int choice = 0;
		
		if(!userFile.exists()) {
			currentUser = firstRun();
			userHashData = hashMapUserData(userFile);
		}
		else
		{
			System.out.println("Hello! Welcome to Adam Lahey's Project Zero!\n\n");
			userHashData = hashMapUserData(userFile);
			currentUser = login(userHashData);
		}
			
		mainMenu(userHashData, currentUser);
		
		codeCleanUp();
	}

}