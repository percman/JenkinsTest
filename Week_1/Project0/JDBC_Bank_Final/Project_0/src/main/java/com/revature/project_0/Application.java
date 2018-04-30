package com.revature.project_0;

import static com.revature.scanner.BankScanner.*;

import com.revature.user.UserLogin;

public class Application {
	
	public static void main(String[] args) {	
		Initialization.initializeData();
		UserLogin.userLogin();
		input.close();
	}
}
