package com.revature.users;

import java.io.Serializable;

public class Student extends Person implements Serializable{

	private static final long serialVersionUID = -5944401087659678976L;
	
	private boolean isLocked;
	private boolean isApproved;
	private int coins;
	private String type = "student";


	public boolean getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}

	public boolean getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	public int getCoins() {
		return coins;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}
	
	
	
}
