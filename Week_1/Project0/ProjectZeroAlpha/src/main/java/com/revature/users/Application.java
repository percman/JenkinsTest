package com.revature.users;

import static com.revature.readwrite.ReadWrite.*;
import static com.revature.users.SerializationOfUsers.*;
import static com.revature.users.GUI.*;
import static com.revature.users.Admin.*;



import java.util.HashMap;

@SuppressWarnings("unused")
public class Application{
	
	public static void main(String[] args) {	
		
		String currentUser = "";
		HashMap<Integer, User> userHashData = new HashMap<>();
		int choice = 0;
		
		userFile.delete();
		
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
		
		// ================= EASY TESTING BELOW =================
		// should be automated using junit, but this is quick and dirty 
		
//		userFile.delete();
//		
//		User p = addUserAsAdmin("adamL", "easypass", true, false, 53.6);
//		User v = addUserAsAdmin("admjl", "ExCeLl3nT_passW0rd", true, false, 573.6);
//		User f = addUserAsAdmin("sfd2", "lkaslkjdf", true, false, 531.6);
//		
//		String username = p.getName();
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
//		System.out.println(adminCheck(userHashData, username));
		
		

		
		
		
		codeCleanUp();
	}

}