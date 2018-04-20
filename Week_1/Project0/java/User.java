package com.revature.project_0;

import java.io.File;
import java.io.Serializable;
import java.util.NoSuchElementException;
import java.util.Scanner;
import org.apache.log4j.Logger;

public class User extends Account implements Serializable {
	private static final Logger logger = Logger.getLogger(Admin.class);
	private static final long serialVersionUID = -7090200429879134748L;
	private boolean locked;
	private double funds;
	
	public User() {}


	public User(String uname, String password, double funds) {
		super(uname,password,false);
		this.funds = funds;
	}
	
	public void UserHome() {
		System.out.println("Welcome "+getUserName());
		System.out.println("Would you like to Withdraw / Deposit / View balance / Sign out\nPlease press w/d/v/ or s");
		Scanner scan = new Scanner(System.in);
		try {
		while(true) {
			String user = scan.nextLine();
			user = user.toLowerCase();
			if(user.equals("w")) {
				System.out.println("How much would you like to withdraw?");
				user = scan.nextLine();
				if(!isStringInteger(user)) {
					System.out.println("\nInvalid input");
					UserHome();
					break;
				}
				else {
					int w = Integer.parseInt(user);
					withdraw(w);
					SerializationOfNewAccount.SerializeAccount(this, new File("src/main/resources/"+getUserName()+".txt"));
					UserHome();
					break;
				}
			}
			else if(user.equals("d")) {
				System.out.println("How much would you like to deposit?");
				user = scan.nextLine();
				if(!isStringInteger(user)) {
					System.out.println("\nInvalid input");
					UserHome();
					break;
				}
				else {
					int w = Integer.parseInt(user);
					deposit(w);
					SerializationOfNewAccount.SerializeAccount(this, new File("src/main/resources/"+getUserName()+".txt"));
					UserHome();
					break;
				}
			}
			else if(user.equals("v")) {
				System.out.println("You have $"+funds+" available funds in your account");
				UserHome();
				break;
			}
			else if(user.equals("s")) {
				Login.menu();
				break;
			}
			else {
				UserHome();
				break;
			}
				
		}
		}catch(NoSuchElementException e) {
			logger.fatal("Scanner not found", e);
		}catch(Exception e) {
			logger.warn(e.getMessage());
		}finally {
			try {
				scan.close();
			}catch(NoSuchElementException e) {
				logger.fatal(e.getMessage());
			}
		}
		
	}
	
	public static boolean isStringInteger(String number ){
	    try{
	        Integer.parseInt(number);
	    }catch(Exception e ){
	        return false;
	    }
	    return true;
	}
	//Deposit into user's account	
	public void deposit(int num) {
		funds += num;
	}
	//Withdraw from user's account
	public void withdraw(int num) {
		if(funds - num < 0)
			System.out.println("You have $"+funds+". Not enough to withdraw $"+num);
		else
			funds -=num;
	}
	//Checks to see if user is locked
	public boolean isLocked() {
		return locked;
	}


	public void setLocked(boolean locked) {
		this.locked = locked;
	}


	public double getFunds() {
		return funds;
	}
	
	public void setFunds(double funds) {
		this.funds = funds;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(funds);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (locked ? 1231 : 1237);
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (Double.doubleToLongBits(funds) != Double.doubleToLongBits(other.funds))
			return false;
		if (locked != other.locked)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "User [locked=" + locked + ", funds=" + funds + "]";
	}


	
	

}
