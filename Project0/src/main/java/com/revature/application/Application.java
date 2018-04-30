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
		MainMenu();
	}
	
	public static void MainMenu() {
	//Print out command options
	System.out.println("=====================");
	System.out.println("Please type number of desired action");
	System.out.println("1. Signup");
	System.out.println("2. Login");
	System.out.println("3. Employee Login");
	System.out.println("0. End Program");

	//Open reader for reading input
	Scanner reading = new Scanner(System.in);
	
	//Read input
	int inputVal = reading.nextInt();
	
	//Close the reader
	reading.close();
	
	//Use user input
	switch(inputVal) {
		case 1:
			//Go to sign up page
			SignUp();
			break;
		case 2:
			//Go to login page
			Login();
			break;
		case 3:
			EmployeeLogin();
			break;
		case 0:
			//End Program
			System.exit(0);
		default:
			//If one of the options wasn't selected, print out error
			//Then return to MainMenu
			System.err.println("Invalid action.");	
			MainMenu();
		}
	}
	
	//Asks for the desired username, password, first name, and last name from the applying customer
	//And stores it in the Applying table
	public static void SignUp() {
		System.out.println("=====================");
		System.out.println("Welcome!");
		System.out.println("To create your new account, please enter your desired"
							+ " username, password, first name, and last name seperated with spaces.");

		Scanner reading = new Scanner(System.in);
		String userName = reading.next();
		String password = reading.next();
		String fname = reading.next();
		String lname = reading.next();
		reading.close();
		
		Applying applying = new Applying(userName, password, fname, lname);
		System.out.println("Did the application go through? " + ApplyingService.insertApplying(applying));
		System.out.println("Returning to Main Menu.");
		MainMenu();
		
	}
	
	public static void Login() {
		System.out.println("=====================");
		System.out.println("Welcome back! Please enter your username and password.");
		
		Scanner reading = new Scanner(System.in);
		String userName = reading.next();
		String password = reading.next();
		reading.close();
		
		Customer customer = new Customer(userName, password);
		
		if (CustomerService.login(customer)) {
			if(lockCheck(customer)) {
				LoginMenu(customer);
			}
			else {
				try {
				throw new UserLockedOutException();
				} catch (UserLockedOutException uloe) {
					System.err.println(uloe.getMessage());
					System.out.println("Returning to Main Menu");
					MainMenu();
				}
				
			}
			
		}
			System.out.println("Sorry, but the username and password that were entered do not match or the user doesn't exist.");
			MainMenu();
	}
		
	public static void EmployeeLogin() {
		System.out.println("=====================");
		System.out.println("Welcome back! Please enter your username and password.");
		
		Scanner reading = new Scanner(System.in);
		String userName = reading.next();
		String password = reading.next();
		reading.close();
		
		Employee employee = new Employee(userName, password);
		
		if (EmployeeService.login(employee)) {
			EmployeeMenu(employee);
		}
			System.out.println("Sorry, but the username and password that were entered do not match or the user doesn't exist.");
			MainMenu();		
	}
	
	public static void LoginMenu(Customer customer) {
		System.out.println("=====================");
		System.out.println("What would you like to do?");
		System.out.println("1. Deposit");
		System.out.println("2. Withdraw");
		System.out.println("3. View Balance");
		System.out.println("0. Logout");
		
		Scanner reading = new Scanner(System.in);
		int inputVal = reading.nextInt();
		reading.close();
		
		switch(inputVal) {
			case 1:
				Deposit(customer);
			case 2:
				Withdraw(customer);
			case 3:
				ViewBalance(customer);
			case 0:
				System.out.println("Thank you, and please have a great day!");
			default:
				System.out.println("Invalid action. Please try again!");
				LoginMenu(customer);
		}
	}
	
	public static void EmployeeMenu(Employee employee) {
		System.out.println("=====================");
		System.out.println("What would you like to do?");
		System.out.println("1. Lock an account");
		System.out.println("2. View applications");
		System.out.println("0. Logout");
		
		Scanner reading = new Scanner(System.in);
		int inputVal = reading.nextInt();
		reading.close();
		
		switch(inputVal) {
			case 1:
				System.out.println("Please enter the id number of the account you would like to lock or unlock.");
				System.out.println("Alternatively, you can type 0 to cancel");

				Scanner reading2 = new Scanner(System.in);
				int id = reading2.nextInt();
				reading2.close();
				
				if (id == 0) {
					EmployeeMenu(employee);
				}
				else {
					changeLock(employee.getId());
				}
				
			case 2:
				ViewApplications(employee);
				break;
			case 0:
				System.out.println("Returning to Main Menu.");
				MainMenu();
				break;
			default:
				System.out.println("Invalid action. Please try again.");
				EmployeeMenu(employee);
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

	public static void Deposit(Customer customer) {
		System.out.println("=====================");
		System.out.println("Ok, how much would you like to deposit (Type without commas)?");
		
		Scanner reading = new Scanner(System.in);
		int amount = reading.nextInt();
		reading.close();
		
		depositCustomer(customer ,amount);
		
	}
	
	public static void Withdraw(Customer customer) {
		System.out.println("=====================");
		System.out.println("Ok, how much would you like to withdraw (Type without commas)?");
		
		Scanner reading = new Scanner(System.in);
		int amount = reading.nextInt();
		reading.close();
		
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
	
	public static void ViewApplications(Employee employee) {
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM customers WHERE ROWNUM = 1");
			ResultSet rs = stmt.executeQuery();
			System.out.println("Would you like to accept " + rs.getString("username") + "'s application?");
			System.out.println("1. Yes");
			System.out.println("2. No");
			System.out.println("0. Return to Employee Menu");
			
			Scanner reading = new Scanner(System.in);
			int inputVal = reading.nextInt();
			reading.close();
			
			switch(inputVal) {
				case 1:
					new Customer( rs.getString("username"), rs.getString("password"),
													rs.getString("fname"), rs.getString("lname"));
					PreparedStatement stmt2 = conn.prepareStatement("DELETE * FROM customers WHERE ROWNUM = 1");
					stmt2.executeQuery();
					ViewApplications(employee);
					break;
				case 2:
					PreparedStatement stmt3 = conn.prepareStatement("DELETE * FROM customers WHERE ROWNUM = 1");
					stmt3.executeQuery();
					ViewApplications(employee);
					break;
				case 0:
					EmployeeMenu(employee);
					break;
				default:
					System.out.println("Invalid action. Please try again.");
					ViewApplications(employee);
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
