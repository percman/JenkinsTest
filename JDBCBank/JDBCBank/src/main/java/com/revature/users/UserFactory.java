package com.revature.users;

import com.revature.exceptions.InvalidUserTypeException;

public class UserFactory {
	
	public static Person getNewUser(String type) throws InvalidUserTypeException {
		
		switch(type.toLowerCase()) {
			case "student":
				return new Student();
			case "teacher":
				return new Teacher();
			case "principal":
				return new Principal();
			default:
				throw new InvalidUserTypeException();	
		}
	}

}
