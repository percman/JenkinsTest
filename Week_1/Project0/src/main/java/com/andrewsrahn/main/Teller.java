package com.andrewsrahn.main;


import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Teller {
	private static Bank bank;
	
	public Teller() {
		Teller.bank = new Bank();
	}
	
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
			System.out.println("Bad user.  retrying...");
			s.close();
			greet();
			break;
		}
	}
	
	public static void neww(Scanner s) {
		System.out.print("\n" +
				"administrator or user? (a/u):");
		switch(s.nextLine()) {
		case "a":
			createAdmin(s);
			break;
		case "u":
			createUser(s);
			break;
		default:
			System.out.println("Bad user. retrying...");
			s.close();
			greet();
			break;
		}
	}
	
	public static void existing(Scanner s) {
		System.out.print("\n" +
				"administrator or user? (a/u):");
		String type = s.nextLine();
		System.out.print("\n" +
				"name:");
		String name = s.nextLine();
		System.out.print("\n" +
				"email:");
		String email = s.nextLine();
		System.out.print("\n" +
				"password:");
		String password = s.nextLine();
		
		switch(type) {
		case "a":
			readAdmin(name, email, password, s);
			break;
		case "u":
			readUser(name, email, password, s);
			break;
		default:
			System.out.println("Bad user. retrying...");
			s.close();
			greet();
			break;
		}
	}

	public static void readUser(String name, String email, String password, Scanner s) {
		User u = Teller.bank.getUser(name);
		if(u != null) {
			System.out.println("\n" +
					name + " found!");
			System.out.println(u.getStatus());
			readBalance(u, s);
		}
		else {
			System.out.println("\n" +
					name + " not found! Creating user...");
			createUser(name, email, password, s);
		}
	}

	public static void readAdmin(String name, String email, String password, Scanner s) {
		Administrator a = Teller.bank.getAdministrator(name);
		if(a != null) {
			System.out.println("\n" +
					name + " found!");
			readStatuses(s, a);
		}
		else {
			System.out.println("\n" +
					name + " not found! Creating administrator...");
			createAdmin(name, email, password, s);
		}
		
	}

	public static void createAdmin(Scanner s) {
		System.out.print("name:");
		String name = s.nextLine();
		System.out.print("\nemail:");
		String email = s.nextLine();
		System.out.print("\npassword:");
		String password = s.nextLine();
		createAdmin(name, email, password, s);
	}
	
	public static void createUser(Scanner s) {
		System.out.print("name:");
		String name = s.nextLine();
		System.out.print("\nemail:");
		String email = s.nextLine();
		System.out.print("\npassword:");
		String password = s.nextLine();
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
		System.out.println("User " + name + " created.  Reading balance...");
		readBalance(u, s);
	}
	
	private static void readBalance(User u, Scanner s) {
		System.out.println(u.getName() + ", " + u.getBalance());
		System.out.println("Update or quit? (u/q):");
		switch(s.nextLine()) {
		case "u":
			updateBalance(u, s);
			break;
		case "q":
			System.out.println("Quitting!");
			s.close();
			System.exit(0);
			break;
		default:
			System.out.println("Bad user. Retrying...");
			readBalance(u, s);
			break;
		}
	}
	
	public static void updateBalance(User u, Scanner s) {
		System.out.println("New balance? (" + Float.MIN_VALUE + " through " + Float.MAX_VALUE + "):");
		float balance = s.nextFloat();
		u.setBalance(balance);
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
				
		System.out.println("Pending");
		pending.forEach( p -> System.out.println( p.getName() ));
		
		System.out.println("Rejected");
		rejected.forEach( r -> System.out.println( r.getName() ));
		
		System.out.println("Approved");
		approved.forEach( p -> System.out.println( p.getName() ));
		
		updateStatuses(s, a);
	}
	
	public static void updateStatuses(Scanner s, Administrator a) {
		System.out.print("Enter name:");
		String name = s.nextLine();
		System.out.print("\nReject, approve, or quit? (r/a/q):");
		String status = s.nextLine();
		
		User u = Teller.bank.getUser(name);
		
		switch(status) {
		case "r":
			if(u != null) {
				u.setRejectedBy(a);
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
				System.out.println("Approved " + u.getName());
			}
			else {
				System.out.println("User not found. Retrying...");
				readStatuses(s, a);
			}
			break;
		case "q":
			System.out.println("Quitting!");
			s.close();
			System.exit(0);
			break;
		default:
			System.out.println("Bad admin. retrying...");
			readStatuses(s, a);
			break;
		}
	}

	public static void main(String[] args) {
		Teller.greet();
	}
}
