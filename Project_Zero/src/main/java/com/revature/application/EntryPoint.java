package com.revature.application;

import com.revature.model.Admin;
import com.revature.model.Customer;
import com.revature.model.User;
import com.revature.service.AdminService;
import com.revature.service.CustomerService;
import com.revature.service.UserService;


public class EntryPoint {

	public static void main(String[] args) {
		
		Application app = new Application();
		app.start();
	}
}
