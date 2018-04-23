package com.revature.project00;

import java.io.Serializable;

import org.apache.log4j.Logger;

public class Customer implements Serializable {
	
	final static Logger log = Logger.getLogger(LoggingHistory.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8395951052314522809L;
	Integer id = 0;
	String firstN;
	String lastN;
	String email;
	String password;
	Double balance;
	Boolean active;
	Boolean administrator;
	
	public Customer () {
		super();
		
	}
	
	public Customer (String afirstN, String alastN, String aemail, String apassword) {
		
		this.id = id++;
		this.firstN = afirstN;
		this.lastN = alastN;
		this.email = aemail;
		this.password = apassword;
		this.balance = 0.0;
		this.active = false;
		this.administrator = false;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstN=" + firstN + ", lastN=" + lastN + ", email=" + email + ", password="
				+ password + ", balance=" + balance + ", active=" + active + ", administrator=" + administrator + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id + 1;
	}


	public String getFirstN() {
		return firstN;
	}

	public void setFirstN(String firstN) {
		this.firstN = firstN;
	}

	public String getLastN() {
		return lastN;
	}

	public void setLastN(String lastN) {
		this.lastN = lastN;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
	public Boolean getAdministrator() {
		return administrator;
	}
	
	public void setAdministrator(Boolean administrator) {
		this.administrator = administrator;
	}
	
	public void deposit(Double balance) {
		this.balance = (this.balance + balance);
		log.info("Deposit Made" );
		
		
	}

	public void withdraw(Double w) {
		this.balance = (this.balance - balance);
		log.info("Withdrawal Made" );
		
	}


}
