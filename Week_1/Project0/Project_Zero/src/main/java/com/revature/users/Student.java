package com.revature.users;

import java.io.Serializable;

public class Student extends Person implements Serializable{

	private static final long serialVersionUID = -5944401087659678976L;
	
	private int coins;
	private String type = "student";


	public int getCoins() {
		return coins;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}
	
	
	
}
