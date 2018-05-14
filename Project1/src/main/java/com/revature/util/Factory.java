package com.revature.util;

import com.revature.model.Employee;
import com.revature.model.Manager;
import com.revature.model.User;

public class Factory {

	public static User getUser(int id, String username, String password, String firstname, char middleInit, String lastName, int managerId) {
		if(managerId==0) {
			return new Employee(id, username, password, firstname, middleInit, lastName);
		}
		else return new Manager(id, username, password, firstname, middleInit, lastName, managerId);
	}
}
