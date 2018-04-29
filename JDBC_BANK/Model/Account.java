package com.revature.model;

import java.io.Serializable;

public class Account implements Serializable {

	private static final long serialVersionUID = 6025811623566886792L;
	
	protected int id;
	protected String userName;
	protected String password;
	protected Double funds;
	protected boolean locked;
	protected boolean permissions;
	protected boolean pending;
	
	public Account() {
		
	}
	
	public Account(int id,String name,String password) {
		super();
		this.id=id;
		this.userName = name;
		this.password = password;
	}
	public Account(String name, String password) {
		super();
		this.userName = name;
		this.password = password;
	}
	
	public Account(String name, String password, Double funds) {
		super();
		this.userName = name;
		this.password = password;
		this.funds = funds;
	}
	
	public Account(String userName, String password, Double funds,boolean locked, boolean permissions, boolean pending) {
		super();
		this.userName = userName;
		this.password = password;
		this.funds = funds;
		this.locked = locked;
		this.permissions = permissions;
		this.pending = pending;
	}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public boolean isPending() {
		return pending;
	}

	public void setPending(boolean pending) {
		this.pending = pending;
	}

	public Double getFunds() {
		return funds;
	}

	public void setFunds(Double funds) {
		this.funds = funds;
	}

	public boolean isAdmin() {
		return permissions;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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
		result = prime * result + ((funds == null) ? 0 : funds.hashCode());
		result = prime * result + id;
		result = prime * result + (locked ? 1231 : 1237);
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + (pending ? 1231 : 1237);
		result = prime * result + (permissions ? 1231 : 1237);
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
		Account other = (Account) obj;
		if (funds == null) {
			if (other.funds != null)
				return false;
		} else if (!funds.equals(other.funds))
			return false;
		if (id != other.id)
			return false;
		if (locked != other.locked)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (pending != other.pending)
			return false;
		if (permissions != other.permissions)
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", userName=" + userName + ", password=" + password + ", funds=" + funds
				+ ", locked=" + locked + ", permissions=" + permissions + ", pending=" + pending + "]";
	}
	

}
