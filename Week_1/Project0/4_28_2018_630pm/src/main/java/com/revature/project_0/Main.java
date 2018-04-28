package com.revature.project_0;
import static com.revature.logger.BankLogger.*;
import static com.revature.scanner.BankScanner.*;

public class Main {
	
	public static void main(String[] args) {	
		Initialization.initializeData();
		UserLogin.userLogin();
		input.close();
	}
}
