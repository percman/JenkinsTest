package com.revature.bankapplication;

import java.io.Serializable;
import java.math.BigDecimal;

public class Customer extends User implements Serializable{

	
	private static final long serialVersionUID = 5404493883386316532L;
	private String username;
	private String password; 
	private BigDecimal accountBalance;
	private int approvalCode;
	private int lockCode;
	private boolean isAdmin;
	private boolean rejected;
	
	public Customer() {}
	
	public Customer(String username, String password) {
		super(username, password);
		this.username = username;
		this.password = password;
		this.accountBalance = new BigDecimal("0");
		this.approvalCode = -1;
		this.lockCode = -1;
		this.isAdmin = false;
		this.rejected = false;
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
	
	public boolean getRejected() {
		return rejected;
	}
	
	public void setRejected(boolean b) {
		this.rejected = b;
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
		int result = super.hashCode();
		result = prime * result + ((accountBalance == null) ? 0 : accountBalance.hashCode());
		result = prime * result + approvalCode;
		result = prime * result + lockCode;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		Customer other = (Customer) obj;
		if (accountBalance == null) {
			if (other.accountBalance != null)
				return false;
		} else if (!accountBalance.equals(other.accountBalance))
			return false;
		if (approvalCode != other.approvalCode)
			return false;
		if (lockCode != other.lockCode)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	
}
