package com.revature.project_0;

import static com.revature.scanner.BankScanner.*;

import com.revature.user.UserLogin;

/**
 * Entry into the program! Have fun with it! Trade account number 2 100 TiCos for a literal pizza!
 * @author Jesse
 *
 */

public class Application {
	
	public static void main(String[] args) {	
		Initialization.initializeData();
		UserLogin.userLogin();
		input.close();
	}
}
