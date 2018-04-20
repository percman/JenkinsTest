package com.revature.project0;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class P0TestFix {

	
	//Start the Program by going to the mainMenu
	public static void main(String[] args) throws IOException{
		mainMenu();
	}

	//List options of possible actions, read and close user input, do action depending on input
	//If input is not used, start from the beginning, 
	public static void mainMenu() throws IOException{
		System.out.println("=====================");
		System.out.println("Please type number of desired action");
		System.out.println("1. Signup");
		System.out.println("2. Login");
		System.out.println("0. End Program");
		
		//open reader for reading input
		Scanner reading = new Scanner(System.in);

		//read input
		int inputVal = reading.nextInt();
		
		//close reader
		reading.close();
		
		//user user input
		switch(inputVal){
			case 1: 
				signUp();//Goto signUp 
				break;
			case 2:
				login();//Goto login
				break;
			case 0:
				System.exit(0);//End program
			default:
				System.out.println("Invalid action");
				mainMenu();
		}
		
	}
	
	//Ask for desired user name, add to Requests.txt, return to main menu
	public static void signUp() throws IOException {
		System.out.println("=====================");
		System.out.println("Welcome! What would you like your username to be?");
		System.out.println("Single word only, please!");
		
		//Open scanner to take in input
		Scanner reading = new Scanner(System.in);
		//Get the name
		String userName = reading.next();
		//Close the scanner
		reading.close();
		
		//Create a new file for Requests.txt if it doesn't exist
		File request = new File("Requests.txt");
		request.createNewFile();

		//Open Requests.txt
		FileWriter out = new FileWriter("Requests.txt");
		
		//Add desired username to, flush, and close Requests.txt 
		out.write(userName + "\n");
		out.flush();
		out.close();
		
		//Return to main menu
		mainMenu();
		
	}

	//Ask for user name, check against Admins.txt and then Accounts.txt, if in neither, tell the user it DNE or was rejected
	public static void login() throws IOException{
		System.out.println("=====================");
		System.out.println("Welcome! Please enter your username!");
		
		//Open scanner for reading user name
		Scanner reading = new Scanner(System.in);
		
		//Read the user name from the user
		String userName = reading.next();
		
		//Close the reader
		reading.close();
		
		//Check the Admins.txt file first
		BufferedReader br = new BufferedReader(new FileReader("Admins.txt"));
		String adminLine;
		while ((adminLine = br.readLine()) != null) {
			if(userName.equals(adminLine)) {
				br.close();
				adminMenu(userName);
			}
		}
		br.close();
		
		//Check the Accounts.txt file next
		BufferedReader br2 = new BufferedReader(new FileReader("Accounts.txt"));
		String accountLine;
		while ((accountLine = br2.readLine()) != null) {
			if(userName.equals(accountLine)) {
				br2.close();
				loginMenu(userName);
			}
		}
		br2.close();
		
		//If the name wasn't in either Admins.txt or Accounts.txt, it DNE or was rejected
		//Go to main menu
		System.out.println("Sorry, but it appeaers that this account doesn't exist or was rejected");
		mainMenu();
	}

	//When a name from Account.txt logs in, they are brought to this menu
	//From here, they can choose an action or to logout
	public static void loginMenu(String userName) throws IOException{
		System.out.println("=====================");
		System.out.println("Welcome, " + userName +"! What action would you like to perform?");
		System.out.println("1. Deposit");
		System.out.println("2. Withdraw");
		System.out.println("3. View Balance");
		System.out.println("0. Logout");
		
		//Open scanner for reading the user's desired action
		Scanner reading = new Scanner(System.in);

		//read input
		int inputVal = reading.nextInt();
		
		//close reader
		reading.close();
		
		//Switch with multiple choices for actions depending on user input
		switch(inputVal){
			case 1: 
				deposit(userName);//Goto deposit
			case 2:
				withdraw(userName);//Goto withdraw
			case 3:
				viewBalance(userName);//Goto viewBalance
			case 0:
				//Return to main menu due to signing out
				System.out.println("Thank you, and have a great day!");
				mainMenu();
			default:
				//If the input was unknown, return to start of this method
				System.out.println("Invalid action! Please try again!");
				loginMenu(userName);
		}
	}
	
	//When a name from Admins.txt loogs in, they are brought to this menu
	//From here, they can choose to view accounts, requests, or logout
	public static void adminMenu(String userName) throws IOException{
		System.out.println("=====================");
		System.out.println("Welcome Administrator " + userName +"! What action would you like to perform?");
		System.out.println("1. View Members");
		System.out.println("2. View Requests");
		System.out.println("0. Logout");

		//open reader
		Scanner reading = new Scanner(System.in);

		//read input
		int inputVal = reading.nextInt();
		
		//close reader
		reading.close();
		
		//Switch to 
		switch(inputVal){
			case 1: 
				viewMembers(userName);//Goto view current members
			case 2:
				viewRequests(userName);//Goto view account requests
			case 0:
				//return to main menu
				mainMenu();
			default:
				System.out.println("Invalid action! Please try again!");
				adminMenu(userName);
		}
		
		
	}
	
	public static void deposit(String userName) {
		System.out.println("=====================");
		System.out.println("OK, how much would you like to deposit?");
	}//
	
	public static void withdraw(String userName) {
		System.out.println("=====================");
		System.out.println("OK, how much would you like to withdraw?");
	}
	
	public static void viewBalance(String userName) {
		System.out.println("=====================");
	}
	
	public static void viewMembers(String userName) throws IOException{
		System.out.println("=====================");
		System.out.println("OK, who are you looking for?");
		
		Scanner reading = new Scanner(System.in);
		
		String searchName = reading.next();
		
		reading.close();
		
		File members = new File("Members.txt");
		members.createNewFile();

		
		
	}
	
	public static void viewRequests(String userName) throws IOException {
		System.out.println("=====================");

		File requests = new File("Requests.txt");
		requests.createNewFile();
		
		File file = new File("Requests.txt");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		StringBuffer sb = new StringBuffer();
		String requester;
		
		while((requester = br.readLine()) != null) {
			System.out.println(requester + " would like to join!");
			System.out.println("Will accept this user's request?");
			System.out.println("1. Accept Request");
			System.out.println("2. Decline Request");
			System.out.println("0. Return to Admin Menu");

			//open reader
			Scanner reading = new Scanner(System.in);

			//read input
			int inputVal = reading.nextInt();
			
			//close reader
			reading.close();
			
			switch(inputVal) {
				//Write requested username to Members.txt
				//Remove requested username from Requests.txt
				//Go back to start
				case 1:
					File members = new File("Members.txt");
					members.createNewFile();

					FileWriter out = new FileWriter("Members.txt");
					
					out.write(userName + "\n");
					out.flush();
					out.close();
					
					
					
					
					
					
					
					break;
				case 2:
					//delete requester from request file, go to next person
					
					
					
					
					
					
					requester=br.readLine();//Goto next request
					break;
				//Return to Admin menu
				case 0:
					br.close();
					adminMenu(userName);
				default:
					System.out.println("Invalid action.");
					viewRequests(userName);//Retry from current request
			}
			
			requester = br.readLine();
		}
		System.out.println("That is all the current requests! Returning to Administrator Menu!");
		adminMenu(userName);
	}
}
