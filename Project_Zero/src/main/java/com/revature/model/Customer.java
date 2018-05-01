package com.revature.model;

import java.io.Serializable;
import java.math.BigDecimal;



public class Customer extends User implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4900815951794560937L;
	private String username;
	private String password; 
	private BigDecimal accountBalance;
	private int approvalCode;
	private int lockCode;
	private boolean isAdmin;
	private int rejected;
	
	public Customer() {}
	
	//Constructor for signing up
	public Customer(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		this.accountBalance = new BigDecimal("0");
		this.approvalCode = 0;
		this.lockCode = 0;
		this.rejected = 0;
	}
	
	//Constructor for inserting Customers
	public Customer(String username, String password, int approvalCode, int lockCode, int rejected, BigDecimal accountBalance) {
		super();
		this.username = username;
		this.password = password;
		this.approvalCode = approvalCode;
		this.lockCode = lockCode;
		this.rejected = rejected;
		this.accountBalance = accountBalance;
	}
	
	
	public boolean approved() {
		if (this.approvalCode == 1)
			return true;
		else
			return false;
	}
	
	
	public int getApprovalCode() {
		return approvalCode;
	}
	
	public void setApprovalCode(int a) {
		this.approvalCode = a;
	}
	
	public int getRejected() {
		return rejected;
	}
	
	public void setRejected(int i) {
		this.rejected = i;
	}
	
	public boolean locked() {
		if (this.lockCode == 1)
			return true;
		else
			return false;
	}
	
	public int getLockCode() {
		return lockCode;
	}
	public void setLockCode(int b) {
		this.lockCode = b;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public BigDecimal getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}
	
	public void setIsAdmin(boolean b) {
		this.isAdmin = b;
	}
	
	public boolean getIsAdmin() {
		return isAdmin;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountBalance == null) ? 0 : accountBalance.hashCode());
		result = prime * result + approvalCode;
		result = prime * result + (isAdmin ? 1231 : 1237);
		result = prime * result + lockCode;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + rejected;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (accountBalance == null) {
			if (other.accountBalance != null)
				return false;
		} else if (!accountBalance.equals(other.accountBalance))
			return false;
		if (approvalCode != other.approvalCode)
			return false;
		if (isAdmin != other.isAdmin)
			return false;
		if (lockCode != other.lockCode)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (rejected != other.rejected)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Customer [username=" + username + ", password=" + password + ", accountBalance=" + accountBalance
				+ ", approvalCode=" + approvalCode + ", lockCode=" + lockCode + ", isAdmin=" + isAdmin + ", rejected="
				+ rejected + "]";
	}

	

	

	
}
