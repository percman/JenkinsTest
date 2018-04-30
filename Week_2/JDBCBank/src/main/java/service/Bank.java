package service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import dao.AdministratorDaoImpl;
import dao.UserDaoImpl;
import model.Administrator;
import model.User;

public class Bank {
	private AdministratorDaoImpl admindao;
	private UserDaoImpl userdao;

	public Bank(Logger logger) {
		admindao = new AdministratorDaoImpl(logger);
		userdao = new UserDaoImpl(logger);
	}

	public Map<String, Administrator> getAdministrators() {
		return admindao.getAdministrators();
	}

	public Administrator getAdministrator(String name) {
		return admindao.getAdministrators().get(name);
	}

	public void createAdmin(Administrator administrator) {
		admindao.createAdministrator(administrator.getName(), administrator.getPassword());
	}

	public Map<String, User> getUsers() {
		return userdao.getUsers();
	}

	public User getUser(String name) {
		return userdao.getUsers().get(name);
	}

	public void createUser(User user) {
		userdao.createUser(user);
	}

	public String authenticateAdministrator(String name, String password) {
		Map<String, Administrator> administrators = admindao.getAdministrators();
		for(Map.Entry<String, Administrator> entry: administrators.entrySet()) {
			Administrator user = entry.getValue();
			if(user.getName().equals(name))
				if(user.getPassword().equals(password))
					return "authenticate";
				else
					return "incorrect password";
		}
		return "administrator not found";
	}

	public void setUser(User user) {
		userdao.createUser(user);
	}

	public String authenticateUser(String name, String password) {
		Map<String, User> users = new HashMap<>();
		for(Map.Entry<String, User> entry: users.entrySet()) {
			User user = entry.getValue();
			if(user.getName().equals(name))
				if(user.getPassword().equals(password))
					if(user.getReject() == null && user.getApprove() == null)
						return "pending";
					else if(user.getReject() != null)
						return "rejected";
					else if(user.getApprove() != null)
						return "approved";
				else
					return "incorrect password";
		}
		return "not found";
	}
}
