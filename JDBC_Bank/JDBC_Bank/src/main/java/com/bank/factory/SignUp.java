package com.bank.factory;

import java.util.Scanner;

import com.bank.exception.IncorrectInputException;
import com.bank.exception.UserNotFoundException;
import com.bank.mainmenu.MainMenu;
import com.bank.model.User;
import com.bank.service.UserService;
import com.bank.singleton.LogSingleton;

public class SignUp {
	
	public static void signUp() throws IncorrectInputException, UserNotFoundException {
		try(Scanner scan = new Scanner(System.in)){
			
			System.out.println("Please answer the following questions.");
			System.out.println("What is your first name?");
			String firstName = scan.nextLine();
			System.out.println("What is your last name?");
			String lastName = scan.nextLine();
			System.out.println("What would you like your password to be?");
			String password = scan.nextLine();
			User temporaryUser = new User(firstName,lastName,password);
			UserService.insertUser(temporaryUser);	
			MainMenu.startMenu();
		}
		
	}


	
}