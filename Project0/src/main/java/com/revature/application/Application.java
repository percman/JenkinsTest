package com.revature.application;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.revature.model.Applying;
import com.revature.model.Customer;
import com.revature.model.Employee;
import com.revature.service.ApplyingService;
import com.revature.service.CustomerService;
import com.revature.service.EmployeeService;
import com.revature.util.ConnectionUtil;

public class Application {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		MainMenu(scanner);
	}
	
	public static void MainMenu(Scanner scanner) {
	//Print out command options
	System.out.println("=====================");
	System.out.println("Please type number of desired action");
	System.out.println("1. Signup");
	System.out.println("2. Login");
	System.out.println("3. Employee Login");
	System.out.println("0. End Program");

	//Open reader for reading input
//	Scanner reading = new Scanner(System.in);
	
	//Read input
	String inputVal = scanner.nextLine();
	
	//Close the reader
//	reading.close();
	
//	if("1".equals(inputVal)) {
//		SignUp();
//	} else 	if("2".equals(inputVal)) {
//		Login();
//	} else 	if("3".equals(inputVal)) {
//		EmployeeLogin();
//	} else 	if("0".equals(inputVal)) {
//		System.out.println("OK, ending program.");
//		System.exit(0);		
//	} else {
//		System.err.println("Invalid action.");	
//		MainMenu();		
//	}
	
	//Use user input
	switch(inputVal) {
		case "1":
			//Go to sign up page
			SignUp(scanner);
			break;
		case "2":
			//Go to login page
			Login(scanner);
			break;
		case "3":
			EmployeeLogin(scanner);
			break;
		case "0":
			//End Program
			scanner.close();
			System.out.println("OK, ending program.");
			System.exit(0);
		default:
			//If one of the options wasn't selected, print out error
			//Then return to MainMenu
			System.err.println("Invalid action.");	
			MainMenu(scanner);
	}
}

	//Asks for the desired username, password, first name, and last name from the applying customer
	//And stores it in the Applying table
	public static void SignUp(Scanner scanner) {
		System.out.println("=====================");
		System.out.println("Welcome!");
		System.out.println("To create your new account, please enter your desired"
							+ " username, password, first name, and last name seperated with spaces.");

//		Scanner reading = new Scanner(System.in);
		String userName = scanner.next();
		String password = scanner.next();
		String fname = scanner.next();
		String lname = scanner.next();
		
		Applying applying = new Applying(userName, password, fname, lname);
		System.out.println("Did the application go through? " + ApplyingService.insertApplying(applying));
		System.out.println("Returning to Main Menu.");
		MainMenu(scanner);
		
	}
	
	public static void Login(Scanner scanner) {
		System.out.println("=====================");
		System.out.println("Welcome back! Please enter your username and password.");
		
		String userName = scanner.next();
		String password = scanner.next();
		
		Customer customer = new Customer(userName, password);
		
		if (CustomerService.login(customer)) {
//			if(lockCheck(customer)) {
				LoginMenu(customer, scanner);
			}
//			else {
//				try {
//				throw new UserLockedOutException();
//				} catch (UserLockedOutException uloe) {
//					System.err.println(uloe.getMessage());
//					System.out.println("Returning to Main Menu");
//					MainMenu(scanner);
//				}
				
//			}
			
//		}
			System.out.println("Sorry, but the username and password that were entered do not match or the user doesn't exist.");
			MainMenu(scanner);
	}
		
	public static void EmployeeLogin(Scanner scanner) {
		System.out.println("=====================");
		System.out.println("Welcome back! Please enter your username and password.");
		
		String userName = scanner.next();
		String password = scanner.next();
		
		Employee employee = new Employee(userName, password);
		
		if (EmployeeService.login(employee)) {
			EmployeeMenu(employee, scanner);
		}
			System.out.println("Sorry, but the username and password that were entered do not match or the user doesn't exist.");
			MainMenu(scanner);		
	}
	
	public static void LoginMenu(Customer customer, Scanner scanner) {
		System.out.println("=====================");
		System.out.println("What would you like to do?");
		System.out.println("1. Deposit");
		System.out.println("2. Withdraw");
		System.out.println("3. View Balance");
		System.out.println("0. Logout");
		
//		Scanner reading = new Scanner(System.in);
		int inputVal = scanner.nextInt();
//		reading.close();
		
		switch(inputVal) {
			case 1:
				Deposit(customer, scanner);
				ViewBalance(customer);
				LoginMenu(customer, scanner);
			case 2:
				Withdraw(customer, scanner);
				ViewBalance(customer);
				LoginMenu(customer, scanner);
			case 3:
				ViewBalance(customer);
				LoginMenu(customer, scanner);
			case 0:
				System.out.println("Thank you, and please have a great day!");
				MainMenu(scanner);
			default:
				System.out.println("Invalid action. Please try again!");
				LoginMenu(customer,scanner);
		}
	}
	
	public static void EmployeeMenu(Employee employee, Scanner scanner) {
		System.out.println("=====================");
		System.out.println("What would you like to do?");
		System.out.println("1. Lock an account");
		System.out.println("2. View applications");
		System.out.println("0. Logout");
		
//		Scanner reading = new Scanner(System.in);
		int inputVal = scanner.nextInt();
//		reading.close();
		
		switch(inputVal) {
			case 1:
				System.out.println("Please enter the id number of the account you would like to lock or unlock.");
				System.out.println("Alternatively, you can type 0 to cancel");

//				Scanner reading2 = new Scanner(System.in);
				int id = scanner.nextInt();
//				reading2.close();
				
				if (id == 0) {
					EmployeeMenu(employee, scanner);
				}
				else {
					changeLock(employee.getId());
				}
				
			case 2:
				ViewApplications(employee, scanner);
				EmployeeMenu(employee, scanner);
				break;
			case 0:
				System.out.println("Returning to Main Menu.");
				MainMenu(scanner);
				break;
			default:
				System.out.println("Invalid action. Please try again.");
				EmployeeMenu(employee, scanner);
				break;
		}
		
	}
	
	private static void changeLock(int id) {
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()){
			CallableStatement stmt = conn.prepareCall("{CALL change_lock(?)}");
			stmt.setInt(++index, id);
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());	
			System.err.println("SQL State: " + sqle.getSQLState());				
			System.err.println("Error Code: " + sqle.getErrorCode());		
		}		
	}

	public static void Deposit(Customer customer, Scanner scanner) {
		System.out.println("=====================");
		System.out.println("Ok, how much would you like to deposit (Type without commas)?");
		
		int amount = scanner.nextInt();
		
		depositCustomer(customer ,amount);
	}
	
	public static void Withdraw(Customer customer, Scanner scanner) {
		System.out.println("=====================");
		System.out.println("Ok, how much would you like to withdraw (Type without commas)?");
		
		int amount = scanner.nextInt();
		
		withdrawCustomer(customer ,amount);
		
	}
	
	public static void ViewBalance(Customer customer) {
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM customers WHERE username = ?");
			stmt.setString(++index, customer.getUsername());
			ResultSet rs = stmt.executeQuery();
			System.out.println("Your balance is: " + rs.getInt("amount"));
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());	
			System.err.println("SQL State: " + sqle.getSQLState());				
			System.err.println("Error Code: " + sqle.getErrorCode());		
		}	
	}
	
	public static void ViewApplications(Employee employee, Scanner scanner) {
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM applying_customers WHERE ROWNUM = 1");
			ResultSet rs = stmt.executeQuery();
			if(!rs.next()) {
				System.out.println("Looks like there are no applicants right now, returning to Employee Menu.");
				EmployeeMenu(employee, scanner);
			}
			else {
			System.out.println("Would you like to accept " + rs.getString("username") + "'s application?");
			System.out.println("1. Yes");
			System.out.println("2. No");
			System.out.println("0. Return to Employee Menu");
			
			int inputVal = scanner.nextInt();
			
			switch(inputVal) {
				case 1:
					
					Customer customer = new Customer( rs.getString("username"), rs.getString("password"),
													rs.getString("fname"), rs.getString("lname"));
					System.out.println("Did the application go through? " + CustomerService.insertCustomer(customer));
					PreparedStatement stmt2 = conn.prepareStatement("DELETE FROM applying_customers WHERE ROWNUM = 1");
					stmt2.executeQuery();
					ViewApplications(employee, scanner);
					break;
				case 2:
					PreparedStatement stmt3 = conn.prepareStatement("DELETE FROM applying_customers WHERE ROWNUM = 1");
					stmt3.executeQuery();
					ViewApplications(employee, scanner);
					break;
				case 0:
					EmployeeMenu(employee, scanner);
					break;
				default:
					System.out.println("Invalid action. Please try again.");
					ViewApplications(employee, scanner);
			}			
			}
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());	
			System.err.println("SQL State: " + sqle.getSQLState());				
			System.err.println("Error Code: " + sqle.getErrorCode());		
		}
	}
	
	public static void depositCustomer(Customer customer, int amount) {
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()){
			CallableStatement stmt = conn.prepareCall("{CALL deposit(?,?)}");
			stmt.setString(++index, customer.getUsername());
			stmt.setInt(++index, amount);
			stmt.executeQuery();
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());	
			System.err.println("SQL State: " + sqle.getSQLState());				
			System.err.println("Error Code: " + sqle.getErrorCode());		
		}	
	}
	
	public static void withdrawCustomer(Customer customer, int amount) {
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()){
			CallableStatement stmt = conn.prepareCall("{CALL withdraw(?,?)}");
			stmt.setString(++index, customer.getUsername());
			stmt.setInt(++index, amount);
			stmt.executeQuery();
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());	
			System.err.println("SQL State: " + sqle.getSQLState());				
			System.err.println("Error Code: " + sqle.getErrorCode());		
		}	
	}
	
	public static boolean lockCheck(Customer customer) {
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM customers WHERE username = ?");
			stmt.setString(++index, customer.getUsername());
			ResultSet rs = stmt.executeQuery();
			if (rs.getInt("isLocked") == 0) {
				return true;
			}
				return false;
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: " + sqle.getSQLState());
			System.err.println("Error Code: " + sqle.getErrorCode());
		}
		return false;
	}
	
}
