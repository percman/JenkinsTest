package com.revature.model;

import java.io.Serializable;

public class CustLogin implements Serializable {
	
	private static final long serialVersionUID = 7942480286416674405L;
	
	String username;
	String passowrd;
	int cid;
	int acno;
	
	CustLogin(){}

	public CustLogin(String username, String passowrd, int cid, int acno) {
		super();
		this.username = username;
		this.passowrd = passowrd;
		this.cid = cid;
		this.acno = acno;
	}
	
	public CustLogin(String username, String passowrd) {
		super();
		this.username = username;
		this.passowrd = passowrd;
	}
	
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassowrd() {
		return passowrd;
	}

	public void setPassowrd(String passowrd) {
		this.passowrd = passowrd;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getAcno() {
		return acno;
	}

	public void setAcno(int acno) {
		this.acno = acno;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + acno;
		result = prime * result + cid;
		result = prime * result + ((passowrd == null) ? 0 : passowrd.hashCode());
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
		CustLogin other = (CustLogin) obj;
		if (acno != other.acno)
			return false;
		if (cid != other.cid)
			return false;
		if (passowrd == null) {
			if (other.passowrd != null)
				return false;
		} else if (!passowrd.equals(other.passowrd))
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
		return "CustLogin [username=" + username + ", passowrd=" + passowrd + ", cid=" + cid + ", acno=" + acno + "]";
	};
	
	

}
