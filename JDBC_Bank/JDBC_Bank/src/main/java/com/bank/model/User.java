package com.bank.model;

import java.io.Serializable;

public class User implements Serializable{

	private static final long serialVersionUID = -6158145816407835864L;
	private String first_name;
	private String last_name;
	private String password;
	private int approved;
	private int locked;
	private int id;
	
	public User(int id) {
		this.id = id;
	}
	
	public User() {}
	
	public User(String first_name,String last_name, String password) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.password = password;
	}
	public User(String first_name, String last_name, int approved, int locked) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.approved = approved;
		this.locked = locked;
	}
	
	public User(String first_name, String last_name, String password, int approved, int locked) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.password = password;
		this.approved = approved;
		this.locked = locked;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getApproved() {
		return approved;
	}

	public void setApproved(int approved) {
		this.approved = approved;
	}

	public int getLocked() {
		return locked;
	}

	public void setLocked(int locked) {
		this.locked = locked;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + approved;
		result = prime * result + ((first_name == null) ? 0 : first_name.hashCode());
		result = prime * result + ((last_name == null) ? 0 : last_name.hashCode());
		result = prime * result + locked;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
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
		if (approved != other.approved)
			return false;
		if (first_name == null) {
			if (other.first_name != null)
				return false;
		} else if (!first_name.equals(other.first_name))
			return false;
		if (last_name == null) {
			if (other.last_name != null)
				return false;
		} else if (!last_name.equals(other.last_name))
			return false;
		if (locked != other.locked)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [first_name=" + first_name + ", last_name=" + last_name + ", password=" + password
				+ ", approved=" + approved + ", locked=" + locked + "]";
	}
	
	

}