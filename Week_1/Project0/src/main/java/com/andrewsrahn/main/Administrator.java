package com.andrewsrahn.main;

public class Administrator extends Person implements Comparable<Administrator>{
	public Administrator(String name, String email, String password) {
		super();
		super.setName(name);
		super.setEmail(email);
		super.setPassword(password);
	}

	@Override
	public int compareTo(Administrator arg0) {
		
		// TODO Auto-generated method stub
		return 0;
	}

}
