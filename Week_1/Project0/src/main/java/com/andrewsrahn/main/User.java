package com.andrewsrahn.main;

public class User extends Person{
	private int balance;
	private boolean locked;
	private Administrator approvedBy;
	private Administrator rejectedBy;
	
	public User(String name, String password, int balance, boolean locked) {
		super();
		super.setName(name);
		super.setPassword(password);
		
		this.balance = balance;
		this.locked = locked;
	}
	public boolean isLocked() {
		return locked;
	}
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	public float getBalance() {
		return balance;
	}
	public Administrator getApprovedBy() {
		return approvedBy;
	}
	public Administrator getRejectedBy() {
		return rejectedBy;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public void setApprovedBy(Administrator approvedBy) {
		this.approvedBy = approvedBy;
	}
	public void setRejectedBy(Administrator rejectedBy) {
		this.rejectedBy = rejectedBy;
	}
	public String getStatus() {
		if(approvedBy == null && rejectedBy == null)
			return "Status: pending review";
		else if(approvedBy != null)
			return "Status: approved by " + approvedBy.getName();
		else if(rejectedBy != null)
			return "Status: rejected by " + rejectedBy.getName();
		else
			return "Status: error";
	}
}

