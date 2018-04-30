package com.revature.user;

import static com.revature.logger.BankLogger.logger;
import static com.revature.scanner.BankScanner.input;

import java.util.ArrayList;
import java.util.List;

import com.revature.factory.UserInterface;
import com.revature.jdbc.AccountService;
import com.revature.jdbc.TiCoService;
import com.revature.jdbc.TradeService;
import com.revature.trade.Trade;

import Exceptions.InsufficientTicosException;
import Exceptions.TiCoAlreadyGeneratedException;

public class UserMenu {
	// All the options available to the user once logged in
	public static void menu(UserInterface currentUser) {
		String choice = "";
		while (choice != "1" && choice != "2" && choice != "3" && choice != "4" && choice != "9") {
			System.out.println("=============");
			System.out.println("  USER MENU");
			System.out.println("=============");
			System.out.println("To display account information\tEnter 1");
			System.out.println("To make a generate a TiCo\tEnter 2");
			System.out.println("To make a trade\t\t\tEnter 3");
			System.out.println("To view pending trades\t\tEnter 4");
			System.out.println("\n\t\tTo log out\tEnter 9");
			System.out.println("\n\n\n\n\n\n\n\n\n\n");
			choice = input.next();
			switch (choice) { 
			
			
			
			case "1": { // Get account information for the user
				System.out.println("=======================");
				System.out.println("  ACCOUNT INFORMATION");
				System.out.println("=======================");
				System.out.println("Account Number: " + currentUser.getAccountNumber());
				System.out.println("Account Holder Name: " + currentUser.getFirstName() + " " + currentUser.getLastName());
				System.out.println("Account username: " + currentUser.getUsername());
				System.out.println("Current Balance: " + TiCoService.getBalance(currentUser.getAccountNumber()) + " TiCos");
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
				System.out.println("Current Balance: " + TiCoService.getBalance(currentUser.getAccountNumber()) + " TiCos");
				System.out.println("\nYou can only generate 1 TiCo per hour");
				System.out.println("\n\tEnter 1 to generate a new TiCo\n");
				if(input.next().equals("1")) {
					System.out.println("Generating...");
					if (!TiCoService.generateTimestamp(currentUser.getAccountNumber())) {
						try {
							throw new TiCoAlreadyGeneratedException();
						} catch (TiCoAlreadyGeneratedException e) {
							break;
						}
					}
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
				System.out.println("Current Balance: " + TiCoService.getBalance(currentUser.getAccountNumber()) + " TiCos");
				System.out.println("Trades can only be offered, not requested");
				System.out.println("\nPlease specify the account number to trade to: ");
				int tradeTo = input.nextInt();
				System.out.println("\nPlease specify how many TiCos to trade: ");
				int tradeAmount = input.nextInt();
				if (TiCoService.getBalance(currentUser.getAccountNumber()) < tradeAmount) {
					try {
						throw new InsufficientTicosException();
					} catch (InsufficientTicosException e) {
						logger.warn(currentUser.getUsername() + " did not have enough TiCos for trade");
						break;
					}
				}
				if (tradeAmount < 1) {
					System.out.println("\n\n\t\tYou must trade at least 1 TiCo!\n");
				}
				TradeService.makeTradeRequest(currentUser.getAccountNumber(), tradeTo, tradeAmount);
				break;
			}
			
			
			
			case "4": { // Allow the user to accept a trade
				System.out.println("===============");
				System.out.println("  USER TRADES");
				System.out.println("===============");
				System.out.println("Current Balance: " + TiCoService.getBalance(currentUser.getAccountNumber()) + " TiCos");
				System.out.println("Trades can only be offered, not requested\n");
				List<Trade> eligibleTrades = new ArrayList<>();
				for (Trade trade : TradeService.getTradeRequest()) {
					if (trade.getIdAcceptor() == currentUser.getAccountNumber()) {
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
					if(requester == trade.getIdRequestor()) {
						tradeAmount = trade.getTradeAmount();
						tradeAvailable = true;
					}
					
				}
				if (!tradeAvailable) {
					System.out.println("\n\n\t\tYou have current trades with the account");
					break;
				}
				System.out.println("\n\tEnter 1 to confirm trade\t\tEnter 2 to reject trade");
				int accept = input.nextInt();
				if(accept == 1) {
					
					TradeService.acceptTradeRequest(requester, currentUser.getAccountNumber(), tradeAmount);
					break;
				}
				else if(accept == 2) {
					TradeService.denyTradeRequest(requester, currentUser.getAccountNumber(), tradeAmount);
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
				System.out.println("Successfully logged out. Bye " + currentUser.getFirstName() + "!");
				logger.trace("User: " + currentUser.getFirstName() + " " + currentUser.getLastName() + " logged out.");
				return;
			}
			}
		}
	}
}