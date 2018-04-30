package com.revature.factory;

import com.revature.admin.Admin;
import com.revature.user.User;

/**
 * Factory class with two possible creations. Note this class in its current implementation is for
 * demonstration purposes only, but if this program were expanded to include multiple types of users,
 * this class would be key in obtaining that implementation
 * @author Jesse
 *
 */

public class UserFactory {

public static UserInterface getUserType(int type) {
		
	switch (type) {
	case 1:
		return new User();	
	case 2:
		return new Admin();
	default:
		System.out.println("Unable to find a user of that type");
		return null;
		}
	}
}
