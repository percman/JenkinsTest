package com.revature.project0;

import java.util.Comparator;

public class UserComparer implements Comparator<User> {
		public int compare(User a, User b){
			return a.username.compareTo(b.username);
		}
}
