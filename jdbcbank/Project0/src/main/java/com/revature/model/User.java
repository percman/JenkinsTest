package com.revature.model;

public class User extends Person{

	private static final long serialVersionUID = 2431287459106300670L;
	private double balance;
	private boolean locked;
	
	public User() {}

	public User(String username, String password) {
		super(username, password);
		this.balance = 0;
		this.locked = false;
	}

	public User(String username, String password, boolean approved, double balance, boolean locked) {
		super(username, password, approved);
		this.balance = balance;
		this.locked = locked;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(balance);
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
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		if (locked != other.locked)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [balance=" + balance + ", locked=" + locked + "]";
	}


	
	
	
}
