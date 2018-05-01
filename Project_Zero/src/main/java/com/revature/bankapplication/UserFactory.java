package com.revature.bankapplication;

import CustomException.UserNotFoundException;

public class UserFactory {

	private final Admin admin = new Admin();
	private final Customer customer = new Customer();
	
	public static User getUser(String userType) throws UserNotFoundException{
		switch(userType) {
		case "Admin":
			return new Admin();
		case "Customer":
			return new Customer();
		default:
			throw new UserNotFoundException("User type you have chosen is not valid.");
		}
	}
}
