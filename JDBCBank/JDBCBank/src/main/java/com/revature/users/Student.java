package com.revature.users;

import java.io.Serializable;

public class Student extends Person implements Serializable {

	private static final long serialVersionUID = -5944401087659678976L;

	private int coins;
	private boolean boughtSubtraction;
	private boolean boughtMultiplication;
	private boolean boughtDivision;

	public int getCoins() {
		return coins;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}

	public boolean isBoughtSubtraction() {
		return boughtSubtraction;
	}

	public void setBoughtSubtraction(boolean boughtSubtraction) {
		this.boughtSubtraction = boughtSubtraction;
	}

	public boolean isBoughtMultiplication() {
		return boughtMultiplication;
	}

	public void setBoughtMultiplication(boolean boughtMultiplication) {
		this.boughtMultiplication = boughtMultiplication;
	}

	public boolean isBoughtDivision() {
		return boughtDivision;
	}

	public void setBoughtDivision(boolean boughtDivision) {
		this.boughtDivision = boughtDivision;
	}

}
