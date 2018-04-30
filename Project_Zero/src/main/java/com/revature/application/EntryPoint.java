package com.revature.application;

import com.revature.model.Admin;
import com.revature.model.Customer;
import com.revature.model.User;
import com.revature.service.AdminService;
import com.revature.service.CustomerService;
import com.revature.service.UserService;


public class EntryPoint {

	public static void main(String[] args) {
		
		/*User adduser = new User("admin", "adminpassword", 1);
		UserService.insertUser(adduser);
		Admin addadmin = new Admin("admin", "adminpassword");
		AdminService.insertAdmin(addadmin);
		
		User adduser2 = new User("Bob", "password", 2);
		UserService.insertUser(adduser2);
		Customer addcustomer2 = new Customer("Bob", "password");
		CustomerService.insertCustomer(addcustomer2);
		
		User adduser3 = new User("Cathy", "password", 2);
		UserService.insertUser(adduser3);
		Customer addcustomer3 = new Customer("Cathy", "password");
		CustomerService.insertCustomer(addcustomer3);
		
		User adduser4 = new User("Dan", "password", 2);
		UserService.insertUser(adduser4);
		Customer addcustomer4 = new Customer("Dan", "password");
		CustomerService.insertCustomer(addcustomer4);*/
		
		Application ui = new Application();
		ui.start();
	}
}
