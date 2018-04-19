package com.revature.project0;
//handle all the login attempts of users
public class Login {
// checks if a user is currently in the list of users	
 static boolean userExists(String name) {
	 for(User user : MovieBarn.users) {
		 if(user.username.equals(name)) {
			 return true;
		 }
	 }
	 return false;
}
 //checks if the password given matches what is on record for the user
 static boolean checkPassword(String pass,String name) {
	 return pass.equals(MovieBarn.passwords.get(name));
}
 //handles running the main login interaction 
public static void login() {
	
}
}
