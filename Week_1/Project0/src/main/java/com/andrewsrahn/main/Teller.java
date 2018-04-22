package com.andrewsrahn.main;

import java.util.Map;
import java.util.Scanner;

public class Teller {
	private static Bank bank;
	private static Scanner scanner;
	
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
		// TODO Auto-generated method stub
		
	}


	private static void createUser() {
		// TODO Auto-generated method stub
		
	}

	private static void loginAdministrator() {
		System.out.println("logging in as an administrator");
		System.out.print("what is your username?:");
		String name = scanner.nextLine();
		System.out.print("what is your password?:");
		String password = scanner.nextLine();
		
		String authenticate = bank.authenticateAdministrator(name, password);
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
		System.out.println(administrator.getName() + "welcome.\n");
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

	private static void exit() {
		System.out.print("exit or retry? (e/r):");
		switch(Teller.scanner.nextLine()) {
		case "e":
			Teller.scanner.close();
			Teller.bank.serialize();
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
		// TODO Auto-generated method stub
		
	}


	public static void main(String[] args) {
		Teller.scanner = new Scanner(System.in);
		Teller.bank = new Bank();
		Teller.greet();
	}
}
