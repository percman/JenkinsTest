package com.revature.bank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.ui.Main;

public class User implements Serializable {
		
	private static final long serialVersionUID = 4074750338527691338L;
	private String uName;
	private String uPwd;
	private boolean userStatus = false;
	private Bank bank;
	  
	
	public User() {}
	
	public boolean isUserStatus() {
		return userStatus;
	}


	public void setUserStatus(boolean userStatus) {
		this.userStatus = userStatus;
	}


	public User(String uName, String uPwd, int acNo) {
		
		this.uName = uName;
		this.uPwd = uPwd;
		this.userStatus = true;	
		bank = new Bank(acNo); 	
		
		BankingSystem.userList.add(this);			/* adding current object reference */
		
	}
	
	
	
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getuPwd() {
		return uPwd;
	}
	public void setuPwd(String uPwd) {
		this.uPwd = uPwd;
	}

	public final static void UserVerify() {
		
		Main.deserialize();
		
		Scanner uN = new Scanner(System.in);
		System.out.print("Please enter user Name ");
		String uName1 = uN.nextLine();
		
		Scanner uP = new Scanner(System.in);
		System.out.print("Please enter user password ");
		String uPwd1 = uP.nextLine();
		for(int i=0; i<BankingSystem.userList.size();i++) {
				
			/*IF VERIFIED*/
			
			
					if(BankingSystem.userList.get(i).getuName().equals(uName1) && 
					BankingSystem.userList.get(i).getuPwd().equals(uPwd1) ) {
						
						if(BankingSystem.userList.get(i).isUserStatus()) {
				
				System.out.println(BankingSystem.userList.get(i).getuName() + " Logged in");
				System.out.println("Please enter options: 1)withdraw	2)deposit	3)Balance  ");
				Scanner o = new Scanner(System.in);
				String option = o.nextLine();
				
				switch(option) {
				
				case "1":
				
						System.out.println("Please enter the amount you want to withdraw");
						Scanner oo = new Scanner(System.in);
						int withdraw = oo.nextInt();		
						BankingSystem.userList.get(i).bank.setWithdraw(withdraw);
						break;
					
					
				
				case "2":
					
						System.out.println("Please enter the amount you want to deposit");
						Scanner ooo = new Scanner(System.in);
						int deposit = ooo.nextInt();		
						BankingSystem.userList.get(i).bank.setDeposit(deposit);
						break;
						

				case "3":
					
						System.out.println("Your balance is: " + BankingSystem.userList.get(i).bank.getBalance() );
						break;
						
	
				default: 
					
					System.out.println("Wrong option");
					System.exit(0);
				} // switch
				
			}
						if(!BankingSystem.userList.get(i).isUserStatus()) {
						System.out.println("your account is locked");
						}
					}
					
		} // for
		
		Main.serializeUser();
				
	}
	
	public static void lockUser() {
		
		Main.deserialize();
		
		if(BankingSystem.userList.isEmpty()) {
			System.out.println("No users in the system to be locked ");
		}
		
		else {
		
		Scanner user = new Scanner(System.in);
		System.out.print("Enter the user name to lock the account ");
		String uName = user.nextLine();
		
		Scanner in = new Scanner(System.in);
		System.out.print("Enter account No to lock the account ");
		int aNo = in.nextInt();
		
		for(int i=0; i<BankingSystem.userList.size();i++) {
			
	
			if(BankingSystem.userList.get(i).getuName().equals(uName) && BankingSystem.userList.get(i).bank.getAccountNo()==aNo) {
				
				System.out.println(BankingSystem.userList.get(i).getuName() + "	 " + "Locked");
				BankingSystem.userList.get(i).setUserStatus(false);
		
			}// if
			
			
		} //for
		
			Main.serializeUser();
		}
	}
	
	public static void unlockUser() {
			Main.deserialize();
		
		if(BankingSystem.userList.isEmpty()) {
			System.out.println("No users in the system to be locked");
		}
		
		else {
		
		Scanner user = new Scanner(System.in);
		System.out.print("Enter the user name to unlock the account");
		String uName = user.nextLine();
		
		Scanner in = new Scanner(System.in);
		System.out.print("Enter account No to unlock the account");
		int aNo = in.nextInt();
		
		for(int i=0; i<BankingSystem.userList.size();i++) {
			
	
			if(BankingSystem.userList.get(i).getuName().equals(uName) && BankingSystem.userList.get(i).bank.getAccountNo()==aNo) {
				
				System.out.println(BankingSystem.userList.get(i).getuName() + "	 " + "unlocked");
				BankingSystem.userList.get(i).setUserStatus(true);
		
			}// if
			
			
		} //for
		
			Main.serializeUser();
		}
		
	}
	
	
	
	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public static void displayUser() {
		
	
		
		Main.deserialize();			// userList points to the user.ser
		
		if(BankingSystem.userList.isEmpty()) {
			System.out.println("No users in the system");
		}
		
		
		System.out.println("user Name |"+ "user password |" + "account status |" + "Account No |" +"balance($)" );  
		  for(int i=0; i < BankingSystem.userList.size(); i++) {
				System.out.println(BankingSystem.userList.get(i).getuName() + "  	        "+ BankingSystem.userList.get(i).getuPwd() + "             " +
						BankingSystem.userList.get(i).isUserStatus() + " 	     " + BankingSystem.userList.get(i).getBank().getAccountNo()+
						"          " +BankingSystem.userList.get(i).getBank().getBalance());
			}
		
	}

}
