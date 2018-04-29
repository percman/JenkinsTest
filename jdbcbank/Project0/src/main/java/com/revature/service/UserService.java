package com.revature.service;

import java.text.DecimalFormat;

import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImpl;
import com.revature.model.Person;
import com.revature.model.User;

public class UserService {

	private static UserDao dao = UserDaoImpl.getInstance();
	
	private UserService(){}
	
	public static boolean insertUser(User user) {
		return dao.insertUser(user);
	}

	public static double getBalance(User user) {
		return dao.getBalance(user);
	}

	public static void withdraw(User user, double cash) {
		if(cash<=0) System.out.println("You must enter a positive number.");
		else if(dao.getBalance(user) >= cash) {
			dao.withdraw(user, cash);
			DecimalFormat df = new DecimalFormat("###.##");
			System.out.println("You have withdrawn $" + cash + " and have $" + df.format(dao.getBalance(user)) + " remaining");
		}
		else System.out.println("You do not have enough in your account");
		
	}

	public static void deposit(User user, double cash) {
		if(cash<=0) System.out.println("You must enter a positive number.");
		else {
			dao.deposit(user, cash);
			DecimalFormat df = new DecimalFormat("###.##");
			System.out.println("You now have $" + df.format(dao.getBalance(user)) + " in your account.");
		}
	}

	public static Person getPerson(String username) {
		return dao.getPerson(username);
	}
	public static String getPassword(String username) {
		return dao.getPassword(username);
	}
	public static String getPasswordHash(User user) {
		return dao.getPasswordHash(user);
	}
	
	public static boolean isApproved(Person person) {
		return dao.isApproved(person);
	}
	
	public static boolean isLocked(Person person) {
		return dao.isLocked(person);
	}
}
