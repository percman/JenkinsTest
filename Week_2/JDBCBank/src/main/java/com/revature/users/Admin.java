package com.revature.users;

import java.io.Serializable;
import java.util.Set;

import com.revature.dao.users.UserService;
import com.revature.exceptions.UserNotFoundException;


public class Admin implements Serializable,NewUser {
	private static final long serialVersionUID = 2650766702984126758L;
	private String username; // the admins username and password
	private String password;
	
	public Admin() {
		
	}
	public Admin(String name, String pass) {
		setUsername(name);
		setPassword(pass);
	}
	
	// approves a user
	public void approve(User user) throws UserNotFoundException {
		UserService.approveUser(user);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Admin other = (Admin) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	

}
