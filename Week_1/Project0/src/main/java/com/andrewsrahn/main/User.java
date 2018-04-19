package com.andrewsrahn.main;

public class User extends Person{
	private float balance;
	private Administrator approvedBy;
	private Administrator rejectedBy;
	
	public User(String name, String email, String password) {
		super();
		this.setName(name);
		this.setEmail(email);
		this.setPassword(password);
		this.setLocked(false);
		
		setBalance(0.00f);
		setApprovedBy(null);
		setRejectedBy(null);
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
	public void setBalance(float balance) {
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
			return "program error";
	}
}
