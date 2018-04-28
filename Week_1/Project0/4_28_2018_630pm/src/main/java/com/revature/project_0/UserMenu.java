package com.revature.project_0;

import static com.revature.logger.BankLogger.logger;
import static com.revature.scanner.BankScanner.input;

import java.util.ArrayList;
import java.util.List;

import com.revature.project_0.JDBC.UserService;

import Exceptions.InsufficientTicosException;

public class UserMenu {
	// All the options available to the user once logged in
	public static void menu(User user) {
		String choice = "";
		while (choice != "1" && choice != "2" && choice != "3" && choice != "4" && choice != "9") {
			System.out.println("=============");
			System.out.println("  USER MENU");
			System.out.println("=============");
			System.out.println("To display account information\tEnter 1");
			System.out.println("To make a generate a TiCo\tEnter 2");
			System.out.println("To make a trade\t\t\tEnter 3");
			System.out.println("To accept a trade\t\tEnter 4");
			System.out.println("\n\t\tTo log out\tEnter 9");
			System.out.println("\n\n\n\n\n\n\n\n\n\n");
			choice = input.next();
			switch (choice) { 
			
			
			
			case "1": { // Get account information for the user
				System.out.println("=======================");
				System.out.println("  ACCOUNT INFORMATION");
				System.out.println("=======================");
				System.out.println("Account Number: " + user.getAccountNumber());
				System.out.println("Account Holder Name: " + user.getFirstName() + " " + user.getLastName());
				System.out.println("Account username: " + user.getUsername());
				System.out.println("Current Balance: " + UserService.getBalance(user.getAccountNumber()) + " TiCos");
				System.out.println("\n\n\t\tEnter any key to return to user menu");
				input.next();
				System.out.println("\n\n\n\n\n\n");
				choice = "0";
				break;
			}
			
			

			case "2": { // Allow the user to generate a time stamp
				System.out.println("==============");
				System.out.println("    TICOS");
				System.out.println("==============");
				System.out.println("Current Balance: " + UserService.getBalance(user.getAccountNumber()) + " TiCos");
				System.out.println("\nYou can only generate 1 timestamp per hour");
				System.out.println("\n\tEnter 1 to generate a new timestamp\n");
				if(input.next().equals("1")) {
					System.out.println("Generating...");
					UserService.generateTimestamp(user.getAccountNumber());
					break;
				}
				else {
					break;
				}
				
			}

			
			
			case "3": { // Allow the user to make a trade
				System.out.println("===============");
				System.out.println("  USER TRADES");
				System.out.println("===============");
				System.out.println("Current Balance: " + UserService.getBalance(user.getAccountNumber()) + " TiCos");
				System.out.println("Trades can only be offered, not requested");
				System.out.println("\nPlease specify the account number to trade to: ");
				int tradeTo = input.nextInt();
				System.out.println("\nPlease specify how many TiCos to trade: ");
				int tradeAmount = input.nextInt();
				if (UserService.getBalance(user.getAccountNumber()) < tradeAmount) {
					try {
						throw new InsufficientTicosException();
					} catch (InsufficientTicosException e) {
						logger.warn(user.getUsername() + " did not have enough TiCos for trade");
						break;
					}
				}
				if (tradeAmount < 1) {
					System.out.println("\n\n\t\tYou must trade at least 1 TiCo!\n");
				}
				UserService.makeTradeRequest(user.getAccountNumber(), tradeTo, tradeAmount);
				break;
			}
			
			
			
			case "4": { // Allow the user to accept a trade
				System.out.println("===============");
				System.out.println("  USER TRADES");
				System.out.println("===============");
				System.out.println("Current Balance: " + UserService.getBalance(user.getAccountNumber()) + " TiCos");
				System.out.println("Trades can only be offered, not requested");
				System.out.println("To accept a trade request\t\tEnter 1\n\n");
				List<Trade> eligibleTrades = new ArrayList<>();
				for (Trade trade : UserService.getTradeRequest()) {
					if (trade.idAcceptor == user.getAccountNumber()) {
						System.out.println(trade);
						eligibleTrades.add(trade);
					}
				}
				if(eligibleTrades.isEmpty()) {
					System.out.println("\n\n\t\tYou have no trade requests\n");
					break;
				}
				System.out.println("Please enter the account you want to trade with: ");
				boolean tradeAvailable = false;
				int tradeAmount = 0;
				int requester = input.nextInt();
				for (Trade trade : eligibleTrades) {
					if(requester == trade.idRequestor) {
						tradeAmount = trade.tradeAmount;
						tradeAvailable = true;
					}
					
				}
				if (!tradeAvailable) {
					System.out.println("\n\n\t\tYou have current trades with the account");
					break;
				}
				System.out.println("\n\tEnter 1 to confirm trade\t\tEnter 2 to reject trade");
				if(input.nextInt() == 1) {
					
					UserService.acceptTradeRequest(requester, user.getAccountNumber(), tradeAmount);
					break;
				}
				else {
					break;
				}
			}

			default: { // catch incorrect entries
				System.out.println("Please enter a valid option");
				break;
			}

			case "9": { // Log out of user menu
				System.out.println("Successfully logged out. Bye " + user.getFirstName() + "!");
				logger.trace("User: " + user.getFirstName() + " " + user.getLastName() + " logged out.");
				return;
			}
			}
		}
	}
}