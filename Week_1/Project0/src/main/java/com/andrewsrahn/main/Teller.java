package com.andrewsrahn.main;

import java.util.Map;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class Teller {
	private static Bank bank;
	private static Scanner scanner;
	private static Logger logger = Logger.getLogger(Teller.class);
	
	private static void greet() {
		System.out.print("do you want to login user, login administrator, "
				+ "create user, or create administrator? (lu/la/cu/ca):");
		
		switch(scanner.nextLine()) {
		case "lu":
			loginUser();
			break;
		case"la":
			loginAdministrator();
		case "cu":
			createUser();
			break;
		case "ca":
			createAdministrator();
			break;
		default:
			System.out.println("Bad input; retrying greet");
			greet();
		}
	}
	
	private static void createAdministrator() {
		System.out.println("creating new administrator");
		System.out.println(Stream.existingAdministrators(Teller.bank.getAdministrators()));
		
		System.out.print("enter administrator name: ");
		String name = scanner.nextLine();
		System.out.print("enter administrator password: ");
		String password = scanner.nextLine();
		System.out.println();
		
		if(Teller.bank.getAdministrator(name).equals(null)) {
			System.out.println("administrator already exists. retrying create administrator");
			createAdministrator();
		}
		else {
			String string = "administrator " + name + " created";
			logger.trace(string);
			Administrator administrator = new Administrator(name, password);
			Teller.bank.createAdmin(administrator);
			System.out.println("entering login...");
			loginAdministrator();
		}
	}

	private static void createUser() {
		System.out.println("creating new user");
		System.out.println(Stream.existingUsers(Teller.bank.getUsers()));
		
		System.out.print("enter user name: ");
		String name = scanner.nextLine();
		System.out.print("enter user password: ");
		String password = scanner.nextLine();
		System.out.println();
		
		if(Teller.bank.getUser(name).equals(null)) {
			System.out.println("user already exists. retrying create user");
			createUser();
		}
		else {
			String string = "user " + name + " created";
			logger.trace(string);
			User user = new User(name, password, 0, false);
			Teller.bank.createUser(user);
			System.out.println("entering login...");
			loginUser();
		}
		
	}

	private static void loginAdministrator() {
		System.out.println("logging in as an administrator");
		System.out.print("what is your username?:");
		String name = scanner.nextLine();
		System.out.print("what is your password?:");
		String password = scanner.nextLine();
		
		String authenticate = bank.authenticateAdministrator(name, password);
		logger.trace("administrator login: " + name + ", " + password + ", " + authenticate);
		switch(authenticate) {
		case "authenticate":
			System.out.println("administrator found. reviewing statuses");
			greetAdministrator(Teller.bank.getAdministrator(name));
			break;
		case "incorrect password":
			System.out.println("administrator found but password incorrect. retrying login administrator...");
			loginAdministrator();
			break;
		case "administrator not found":
		default:
			System.out.println("administrator not found. retrying login administrator...");
			loginAdministrator();
			break;
		}
	}
	
	private static void greetAdministrator(Administrator administrator) {
		System.out.println(administrator.getName() + " welcome.\n");
		System.out.print("do you want to update users or exit? (u/e):");
		
		switch(Teller.scanner.nextLine()) {
		case "u":
			System.out.println("updating...\n");
			updateUsers(administrator);
			break;
		case "e":
			System.out.println("ending...\n");
			exit();
			break;
		default:
			System.out.println("bad input. retrying greet administrator...");
			greetAdministrator(administrator);
			break;
		}
	}
	
	private static void greetUser(User user) {
		System.out.println(user.getName() + " welcome.\n");
		System.out.println("do you want to enter transaction or exit? (t/e):");
		
		switch(Teller.scanner.nextLine()) {
		case "t":
			System.out.println("Entering transaction...");
			transactUser(user);
			break;
		case "e":
			System.out.println("Entering exit...");
			exit();
			break;
		default:
			System.out.println("Bad input. retrying greet user...");
			greetUser(user);
			break;
		}
	}


	private static void transactUser(User user) {
		System.out.println();
		System.out.println("your balance is " + user.getBalance());
		System.out.print("add or subtract 20? (a/s):");
		
		switch(scanner.nextLine()) {
		case "a":
			int newBalance = user.getBalance() + 20;
			user.setBalance(newBalance);
			Teller.bank.setUser(user);
			String message = user.getName() + " adding 20 to account. New balance: " 
					+ Integer.toString(newBalance);
			logger.trace(message);
			greetUser(user);
			break;
		case "s":
			newBalance = user.getBalance() - 20;
			user.setBalance(newBalance);
			Teller.bank.setUser(user);
			message = user.getName() + " subtracting 20 to account.\nNew balance: "
					+ Integer.toString(newBalance);
			logger.trace(message);
			greetUser(user);
			break;
		default:
			System.out.println("bad input. retrying transact user");
			transactUser(user);
		}
	}

	private static void exit() {
		System.out.print("exit or retry? (e/r):");
		switch(Teller.scanner.nextLine()) {
		case "e":
			Teller.scanner.close();
			Teller.bank.serialize(logger);
			logger.trace("logout");
			System.out.println("goodbye!");
			break;
		case "r":
			greet();
			break;
		default:
			System.out.println("bad input. retrying exit...");
			exit();
		}
	}

	private static void updateUsers(Administrator administrator) {
		System.out.println("users on the system include");
		Map<String, User> users = Teller.bank.getUsers();
		System.out.println(Stream.pendingUsers(users));
		System.out.println(Stream.approvedUsers(users));
		System.out.println(Stream.rejectedUsers(users));
		System.out.println(Stream.lockedUsers(users));
		System.out.println(Stream.unlockedUsers(users));
		
		updateUser(administrator);
	}

	private static void updateUser(Administrator administrator) {
		System.out.print("which user do you want to update?:");
		String name = Teller.scanner.nextLine();
		User user = Teller.bank.getUser(name);
		
		if(user == null) {	
			System.out.println("user not found. retrying update");
			updateUser(administrator);
		} else {
			logger.trace("administrator: " + administrator.getName() + " updated " + user.getName());
			System.out.println("updating " + user.getName());
			System.out.println("approve, reject, lock, or unlock? (a/r/l/u):");
			
			switch(Teller.scanner.nextLine()) {
			case "a":
				user.setApprovedBy(administrator);
				System.out.println(user.getName() + " approved");
				break;
			case "r":
				user.setRejectedBy(administrator);
				System.out.println(user.getName() + " rejected");
				break;
			case "l":
				user.setLocked(true);
				System.out.println(user.getName() + " locked");
				break;
			case "u":
				user.setLocked(false);
				System.out.println(user.getName() + " unlocked");
				break;
			default:
				System.out.println("bad input. retrying update user");
				updateUsers(administrator);
			}
			Teller.bank.setUser(user);
		}
		
		System.out.println("Update another user or exit? (u/e):");
		switch(Teller.scanner.nextLine()) {
		case "u":
			updateUsers(administrator);
			break;
		case "e":
			exit();
			break;
		default:
			System.out.println("bad input. retrying update users...");
			updateUsers(administrator);
		}
	}

	private static void loginUser() {
		System.out.println("logging in as a user");
		System.out.print("what is your username?:");
		String name = scanner.nextLine();
		System.out.print("what is your password?:");
		String password = scanner.nextLine();
		String authenticate = bank.authenticateUser(name, password);
		switch(authenticate) {
		case "pending":
			String message = name + " is pending approval. cannot login.";
			logger.warn(message);
			exit();
			break;
		case "rejected":
			message = name + " is rejected. cannot login.";
			logger.warn(message);
			exit();
			break;
		case "approved":
			message = name + " is approved. continuing to greet.";
			logger.trace(message);
			greetUser(Teller.bank.getUser(name));
			break;
		case "not found":
			message = name + " not found. retrying login user...";
			logger.trace(message);
			loginUser();
			break;
		case "incorrect password":
			message = name + " found but password incorrect. retrying login user...";
			logger.trace(message);
			loginUser();
			break;
		default:
			System.out.println("Bad input. retrying login user.");
			loginUser();
		}
	}


	public static void main(String[] args) {
		Teller.scanner = new Scanner(System.in);
		Teller.bank = new Bank(logger);
		Teller.greet();
	}
}
