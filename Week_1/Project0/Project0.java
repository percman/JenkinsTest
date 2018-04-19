package com.revature.project0;

public class Project0 {

	public static void mainMenu() {
		//take in input for desired instruction
		//signup
		//login
	}
	
	public static void signup() {
		//ask for desired id- name? num?
		//add to id to request file
	}
	
	public static void login() {
		//ask for id
			//if id is in accounts file
				//go to loginMenu
			//else if id is admin account
				//go to adminMenu
			//else
				//return error
				//mainMenu
	}
	
	public static void loginMenu() {
		//ask for desired action
		//switch
			//1. Deposit
			//2. Withdraw
			//3. Balance
			//4. Logout
	}
	
	public static void adminMenu() {
		//ask for desired action
		//switch
			//1. Deposit
				//goto deposit
			//2. Withdraw
				//goto withdraw
			//3. Balance
				//goto balance
			//4. Logout
				//goto mainMenu
			//5. View member accounts
				//goto members
			//6. View request accounts
				//goto requests
	}
	
	public static void members() {
		//if file is empty
			//say it is empty
			//return to mainMenu
		//else
			//display the top request
			//give choices
				//accept
					//add request id to members
					//remove request id from requests
				//reject
					//add request id to rejects
					//remove request id from requests
			//skip? put id on bottom of request file
	}
	
	public static void requests() {
	
	}
	
	
	
	public static void deposit() {
		//ask how much to deposit
		//add amount to account
		//show updated amount
		//goto mainMenu
	}
	
	public static void withdraw() {
		//ask how much to withdraw
		//check if amount is in account
		  	//if not, display error
				//goto mainMenu
			//if there is enough
				//remove amount from account
				//show updated amount
				//goto mainMenu
	}
	
	public static void balance() {
		//show current amount
		//goto mainMenu
	}
	
	
	
	public static void main(String[] args) {
		//goto mainMenu()
	}
	
}
