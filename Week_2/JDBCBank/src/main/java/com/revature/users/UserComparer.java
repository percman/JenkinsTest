package com.revature.users;

import java.util.Comparator;

public class UserComparer implements Comparator<User> {
		public int compare(User a, User b){
			return a.getUsername().compareTo(b.getUsername());
		}
}
