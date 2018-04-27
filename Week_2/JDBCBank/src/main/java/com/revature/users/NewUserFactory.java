package com.revature.users;

import com.revature.exceptions.UserTypeNotFoundException;

public class NewUserFactory {

		public static NewUser getUser(String type,String name,String password) throws UserTypeNotFoundException {
			switch(type.toLowerCase()) {
			case "admin":
				return new Admin(name,password);
			case "user":
				return new User(name,password);
			default:
				throw new UserTypeNotFoundException("That is not an existing user type");
			}
		}
		
	}

