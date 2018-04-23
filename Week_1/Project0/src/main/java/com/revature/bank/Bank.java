package com.revature.bank;
import java.io.Serializable;
import java.util.Scanner;

public class Bank implements Serializable  {

	private static final long serialVersionUID = 8644090106756759257L;
	private int accountNo;
	private double balance;
	
	/*Opens account with minimum $100 balance*/
	public Bank(int accountNo) {
		
		this.accountNo = accountNo;
		this.balance = 100 ;					//Sets initial account balance to 100
		System.out.println("balance " + this.balance);
	
	}
	
	/*Opens account with minimum $100 balance & adds additional balance*/
	public Bank(int accountNo, int firstDeposit) {

		this.accountNo = accountNo;
		this.balance = 100 + firstDeposit ;					//Sets initial account balance to 100
	
	}

	public int getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	
	public void setDeposit(int newDeposit) {
		this.balance = this.balance + newDeposit;
	}
	
	public void setWithdraw(int withdraw) {
		this.balance = this.balance - withdraw;
	}

}
