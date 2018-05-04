package com.revature.model;

public class OtherEmployee extends Employee implements Worker {
	
	public OtherEmployee() {};
	
	public OtherEmployee(int id, String title,String username,String password) {
		super(id,title,username,password);
	}

	
}
