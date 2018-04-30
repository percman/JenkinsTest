package com.bank.factory;

import java.util.Scanner;

import com.bank.exception.UserNotFoundException;
import com.bank.mainmenu.MainMenu;
import com.bank.model.User;
import com.bank.service.UserService;
import com.bank.singleton.LogSingleton;

public class AdminLogin {

	public static void adminLogin(){
		LogSingleton.getInstance();			
			System.out.println("Welcome to the admin login!");		
	
		
		
//		MainMenu.startMenu();
	}
}
