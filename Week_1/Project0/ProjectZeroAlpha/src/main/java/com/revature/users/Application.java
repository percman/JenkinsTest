package com.revature.users;

import static com.revature.readwrite.ReadWrite.*;
import static com.revature.users.SerializationOfUsers.*;


import java.util.HashMap;

@SuppressWarnings("unused")
public class Application extends GUI{
	
	public static void main(String[] args) {	
		
		userFile.delete();
//		
//		
		if(!userFile.exists())
			firstRun();

		// Create a HashMap of all users
		// this is created here so it does not have to be recreated multiple times 
		// can query this until an account is changed 
		HashMap<Integer, User> userHashData = new HashMap<>();
		userHashData = hashMapUserData(userFile);


		System.out.println("Hello! Welcome to Adam Lahey's Project Zero!\n\n");
		String currentUser = login(userHashData);
		mainMenu(userHashData, currentUser);
		
		// ================= EASY TESTING BELOW =================
		// should be automated using junit, but this is quick and dirty 
		
//		userFile.delete();
//		
//		User p = addUserAsAdmin("adamL", "easypass", true, false, 53.6);
//		User v = addUserAsAdmin("admjl", "ExCeLl3nT_passW0rd", true, false, 573.6);
//		User f = addUserAsAdmin("sfd2", "lkaslkjdf", true, false, 531.6);
//		
//						
//		HashMap<Integer, User> userHashData = new HashMap<>();
//				
//		userHashData = hashMapUserData(userFile);
//		
//		System.out.println(userHashData);
//		System.out.println(userHashData.size());
//		
//		System.out.println("===========");
//		
//		System.out.println(passwordCheck(userHashData, p.getName(), p.getPassword()));
//		
		

		
		
		
		codeCleanUp();
	}

}