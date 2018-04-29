package com.revature.model;

import com.revature.exception.InvalidInputException;

public class AccountFactory {

	public static Menu createAccount(Account account, boolean isAdmin) throws InvalidInputException {
		String value = String.valueOf(isAdmin);
		switch(value) {
			case "true":
				return new Admin(account.getUserName(),account.getPassword());
			case "false":
				return new User(account.getUserName(),account.getPassword());
			default:
				throw new InvalidInputException("This is neither an admin or a user");
				
		}
	}
}
