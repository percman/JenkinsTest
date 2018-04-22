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
		
		User p = addUser("adamL", "easypass", true, true, 53.6);
		User v = addUser("admjl", "ExCeLl3nT_passW0rd", false, false, 573.6);
		User f = addUser("neat", "guy", true, false, 531.6);
		User a = addUser("anything", "true", true, false, 100);
		User b = addUser("what", "thing", true, false, 200);
		User c = addUser("dude", "no", true, true, 300);
		
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