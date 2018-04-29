package com.revature.factory;

import com.revature.admin.Admin;
import com.revature.user.User;

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
