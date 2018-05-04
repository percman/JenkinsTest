package com.revature.model;

public class FinancialManager extends Employee implements Worker {

	public FinancialManager() {};
	
	public FinancialManager(int id, String title,String username, String password) {
		super(id,title,username,password);
	}
		
}
