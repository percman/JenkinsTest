package com.andrewsrahn.main;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Teller {
	private static Bank bank;
	
	public static void greet() {
		Scanner s = new Scanner(System.in);
		
		System.out.print("new or existing? (n/e):");
		switch(s.nextLine()) {
		case "n":
			neww(s);
			break;
		case "e":
			existing(s);
			break;
		default:
			System.out.println("Bad user.  retrying...\n");
			s.close();
			greet();
			break;
		}
	}
	
	public static void neww(Scanner s) {
		System.out.print("administrator or user? (a/u):");
		switch(s.nextLine()) {
		case "a":
			createAdmin(s);
			break;
		case "u":
			createUser(s);
			break;
		default:
			System.out.println("Bad user. retrying...\n");
			s.close();
			greet();
			break;
		}
	}
	
	public static void existing(Scanner s) {
		System.out.print("administrator or user? (a/u):");
		
		switch(s.nextLine()) {
		case "a":
			readAdmin(s);
			break;
		case "u":
			readUser(s);
			break;
		default:
			System.out.println("Bad user. Retrying existing...\n");
			existing(s);
			break;
		}
	}

	public static void readUser(Scanner s) {
		List<User> users = Teller.bank.getUsers().values().stream().collect(Collectors.toList());
		String name = "", email = "", password = "";
		users.forEach( u -> System.out.println( ));
		User u = Teller.bank.getUser(name);
		if(u != null) {
			System.out.println(name + " found!\n");
			System.out.println(u.getStatus());
			readBalance(u, s);
		}
		else {
			System.out.println(name + " not found! Creating user...\n");
			createUser(name, email, password, s);
		}
	}

	public static void readAdmin(Scanner s) {
		String name = "", email = "", password = "";
		Administrator a = Teller.bank.getAdministrator(name);
		if(a != null) {
			System.out.println(name + " found!\n");
			readStatuses(s, a);
		}
		else {
			System.out.println(name + " not found! Creating administrator...\n");
			createAdmin(name, email, password, s);
		}
	}

	public static void createAdmin(Scanner s) {
		System.out.print("name:");
		String name = s.nextLine();
		System.out.print("email:");
		String email = s.nextLine();
		System.out.print("password:");
		String password = s.nextLine();
		System.out.println("");
		createAdmin(name, email, password, s);
	}
	
	public static void createUser(Scanner s) {
		System.out.print("name:");
		String name = s.nextLine();
		System.out.print("email:");
		String email = s.nextLine();
		System.out.print("password:");
		String password = s.nextLine();
		System.out.println("");
		createUser(name, email, password, s);		
	}
	
	public static void createAdmin(String name, String email, String password, Scanner s) {
		Administrator a = new Administrator(name, email, password);
		Teller.bank.createAdmin(a);
		System.out.println("Administrator " + name + " created.");
		readStatuses(s, a);
	}
	
	public static void createUser(String name, String email, String password, Scanner s) {
		User u = new User(name, email, password);
		Teller.bank.createUser(u);
		System.out.println(name + " created as user.  Reading balance...");
		readBalance(u, s);
	}
	
	public static void readBalance(User u, Scanner s) {
		System.out.println(u.getName() + ", " + u.getBalance());
		System.out.println("Transaction or quit? (t/q):");
		switch(s.nextLine()) {
		case "t":
			updateBalance(u, s);
			break;
		case "q":
			exit(s);
			break;
		default:
			System.out.println("Bad user. Retrying...");
			readBalance(u, s);
			break;
		}
	}
	
	public static void updateBalance(User u, Scanner s) {
		System.out.print("Add or subtract $20? (a/s):");
		
		switch(s.nextLine()) {
		case "a":
			u.setBalance(20);
			break;
		case "s":
			u.setBalance(-20);
			break;
		default:
			System.out.println("Bad user. Retrying transaction...");
		}
		Teller.bank.setUser(u);
		readBalance(u, s);
	}

	public static void readStatuses(Scanner s, Administrator a) {
		Map<String, User> users = Teller.bank.getUsers();
		
		List<User> pending = users.values().stream()
				.filter(u -> u.getApprovedBy() == null)
				.filter(u -> u.getRejectedBy() == null)
				.collect(Collectors.toList());
		
		List<User> rejected = users.values().stream()
				.filter( u -> u.getApprovedBy() == null)
				.filter( u -> u.getRejectedBy() != null)
				.collect(Collectors.toList());
		
		List<User> approved = users.values().stream()
				.filter( u -> u.getApprovedBy() != null)
				.filter( u -> u.getRejectedBy() == null)
				.collect(Collectors.toList());
				
		System.out.print("Pending [");
		pending.forEach( p -> System.out.print( p.getName() + ", " ));
		System.out.println("]");
		
		System.out.print("Rejected [");
		rejected.forEach( r -> System.out.print( r.getName() + ", "));
		System.out.println("]");
		
		System.out.print("Approved [");
		approved.forEach( p -> System.out.print( p.getName() + ", "));
		System.out.println("]");
		
		updateStatuses(s, a);
	}
	
	public static void updateStatuses(Scanner s, Administrator a) {
		System.out.print("Enter name of user to reject or approve:");
		String name = s.nextLine();
		System.out.print("\nReject or approve? (r/a):");
		String status = s.nextLine();
		
		User u = Teller.bank.getUser(name);
		
		switch(status) {
		case "r":
			if(u != null) {
				u.setRejectedBy(a);
				Teller.bank.setUser(u);
				System.out.println("Rejected " + u.getName());
			}
			else {
				System.out.println("User not found. Retrying...");
				readStatuses(s, a);
			}
			break;
		case "a":
			if(u != null) {
				u.setApprovedBy(a);
				Teller.bank.setUser(u);
				System.out.println("Approved " + u.getName());
			}
			else {
				System.out.println("User not found. Retrying...");
				readStatuses(s, a);
			}
			break;
		default:
			System.out.println("Bad admin. retrying...");
			readStatuses(s, a);
			break;
		}
		
		System.out.println("");
		readStatuses(s, a);
	}
	
	public static void farewell(Scanner s, Administrator a) {
		System.out.print("Quit or review users? (q/r):");
		
		switch(s.nextLine()) {
		case "q":
			exit(s);
			break;
		case "r":
			readStatuses(s, a);
			break;
		default:
			System.out.println("Bad admin. Retrying farewell...");
			farewell(s, a);
			break;
		}
	}
	
	public static void farewell(Scanner s, User u) {
		System.out.print("Quit or read balance? (q/r):");
		
		switch(s.nextLine()) {
		case "q":
			exit(s);
			break;
		case "r":
			readBalance(u, s);
			break;
		default:
			System.out.println("Bad user. Retrying farewell...");
			farewell(s, u);
			break;
		}
	}
	
	public static void exit(Scanner s) {
		System.out.println("Quiting; Thank you for using project 0! -andrew");
		s.close();
		Serialize.serialize(Teller.bank, new File("src/main/resources/bank.txt"));
		System.exit(0);
	}

	public static void main(String[] args) {
		Teller.bank = new Bank();
		Teller.bank.initialize();
		Teller.greet();
	}
}
