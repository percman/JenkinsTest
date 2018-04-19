package com.revature.project0;

public class NewUserFactory {

		public static NewUser getUser(String user,String name,String password) throws UserTypeNotFoundException {
			switch(name.toLowerCase()) {
			case "admin":
				return new Admin(name,password);
			case "user":
				return new User(name,password);
			default:
				throw new UserTypeNotFoundException("That is not ann existing user type");
			}
		}
		
	}

