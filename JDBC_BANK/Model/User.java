package com.revature.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.log4j.Logger;

import com.revature.application.Login;
import com.revature.exception.OverDraftException;
import com.revature.service.AccountService;

public class User extends Account implements Serializable, Menu {
	private static final long serialVersionUID = -6061328647424255029L;
	private static final Logger logger = Logger.getLogger(Admin.class);
	
	public User() {}
	
	public User(String username,String password, Double funds, boolean locked, boolean permission, boolean pending) {
		super(username,password,funds,locked,permission,pending);
	}
	
	public User(String username, Boolean pending) {
		this.userName = username;
		this.pending = pending;
	}
	
	public User(String username, String password) {
		super(username,password);
	}
	
	public User(String username) {
		this.userName = username;
	}
	
	
	public User(int id, String username, Double funds) {
		this.id = id;
		this.userName = username;
		this.funds = funds;
	}

	/*
	 * User can create another view his accounts to withdraw from or deposit into, as well as create another account and deposit
	 * money into it
	 */
	public void Home(Menu u) {
		User user = (User) u;
		List<User> accounts = AccountService.getAccounts(user);
		System.out.println("Would you like to View accounts / Create another account\nPlease press v / c / or any key to sign out");
		try {
			String input = Login.scan.nextLine();
			input = input.toLowerCase();
			switch(input) {
				case "v":
					System.out.println("These are your accounts ");
					List<Integer> id = new ArrayList<>();
					for(User acc : accounts) {
						System.out.println("ID: "+acc.getId()+" , Amount: "+acc.getFunds());
						id.add(acc.getId());
					}
					System.out.println("Select the id of the account you want to access: ");
					int accnum = Login.scan.nextInt();
					if(!id.contains(accnum))
						System.out.println("That id does not belong to any of your accounts");
					else {
						access(accnum ,user);
					}
					break;
				case "c":
					System.out.println("Enter the amount of money you want to deposit into your new account");
					String check = Login.scan.nextLine();
					if(!isStringInteger(check))
						System.out.println("That is not a valid amount");
					else {
						Double funds = Double.parseDouble(check);
						user.setFunds(funds);
						AccountService.insertAccount(user);
						System.out.println("Your new account is set");
					}
					break;
			default :
				Login.menu();
			}
		}catch(NoSuchElementException e) {
			logger.warn("Scanner not found", e);
		}catch(Exception e) {
			logger.warn(e.getMessage());
		}
		
	}
	
	private static void access(int id, User user ){
		Login.scan.nextLine();
		System.out.println("Would you like to Deposit or withdraw? Press d or w or any key to sign out: ");
		String input = Login.scan.nextLine();
		Double funds = AccountService.getBalance(id, user);
		switch(input) {
			case "d":
				System.out.println("How much would you like to deposit? ");
				Double deposit = format(Login.scan.nextDouble());
				AccountService.setFunds(id, funds+deposit);
				System.out.println("Your new balance is "+AccountService.getBalance(id, user));
				break;
			case "w":
				System.out.println("Enter the amount you would like to withdraw: $");
				Double withdrawal = format(Login.scan.nextDouble());
				try {
				if(withdrawal > funds)
					throw new OverDraftException("You dont have enough to witdraw that amount");
				else {
					AccountService.setFunds(id, funds - withdrawal);
					System.out.println("Your new balance is "+AccountService.getBalance(id, user));
				}
				}catch(OverDraftException ode) {
					logger.warn("OverDraftException");
				}
				break;
			default :
				user.Home(user);
		}
		
	}
	
	public static boolean isStringInteger(String number ){
	    try{
	        Integer.parseInt(number);
	    }catch(NumberFormatException nfe){
	    	logger.warn(nfe.getMessage());
	        return false;
	    }
	    return true;
	}
	//Formats numeric input	
	public static Double format(Double num) {
		String strDouble = String.format("%.2f", num);
		num = Double.valueOf(strDouble);
		return num;
	}
}
