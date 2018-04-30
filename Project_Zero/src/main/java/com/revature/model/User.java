package com.revature.model;

import java.io.Serializable;



public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4664592853125586224L;
	private int id;
	private String username;
	private String password;
	private int typeId;
	
	public User() {}
	
	// Constructor used for logging in
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	// Constructor used for inserting 
	public User(String username, String password, int typeId) {
		super();
		this.username = username;
		this.password = password;
		this.typeId = typeId;
	}
	
	
	// Constructor used for getting users
	public User(int id, String username, String password, int typeId) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.typeId = typeId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + typeId;
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
		User other = (User) obj;
		if (id != other.id)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (typeId != other.typeId)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", typeId=" + typeId + "]";
	}
	
	
	
	
	
	
	
}
