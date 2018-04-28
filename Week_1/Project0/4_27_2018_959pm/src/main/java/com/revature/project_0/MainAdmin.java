package com.revature.project_0;

public class MainAdmin extends User {

	// Currently this class is not in use
	// Code to make a singleton
	private static final long serialVersionUID = -2161429192115620452L;
	private static MainAdmin instance;
	private MainAdmin() {
		
	}
	public static MainAdmin getInstance() {
		if(instance == null) {
			instance = new MainAdmin();
		}
		return instance;
	}

	public String returnString() {
		return "MainAdmin";
	}
}
