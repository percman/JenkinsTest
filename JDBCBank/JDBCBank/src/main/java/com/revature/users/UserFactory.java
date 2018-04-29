package com.revature.users;

import com.revature.exceptions.InvalidUserTypeException;

public class UserFactory {
	
	public static void getNewUser(String type) throws InvalidUserTypeException {
		
		switch(type.toLowerCase()) {
			case "student":
				// go to student login
			case "teacher":
				// go to teacher login
			case "principal":
				// go to principal login
			default:
				throw new InvalidUserTypeException();	
		}
	}

}
