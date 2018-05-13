package com.revature.model;

public class FinancialManager extends Employee {

	private static final long serialVersionUID = -4284854441919500638L;

	// This is the only field a financial manager has that an employee does not
	private int fmid;

	// Public no-arg constructor
	public FinancialManager() {
		super();
	}

	public FinancialManager(String username) {
		super(username);
		// TODO Auto-generated constructor stub
	}

	// Public constructor that takes the financial manager's username and password
	public FinancialManager(String username, String password) {
		super(username, password);
	}

	// Public constructor that takes all fields
	public FinancialManager(int fmid, int id, String username, String password, boolean isFinancialManager,
			String firstname, String middleInitial, String lastname, String phone, String email) {
		super(id, username, password, isFinancialManager, firstname, middleInitial, lastname, phone, email);
		this.fmid = fmid;

	}

	public int getFmid() {
		return fmid;
	}

	public void setFmid(int fmid) {
		this.fmid = fmid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + fmid;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		FinancialManager other = (FinancialManager) obj;
		if (fmid != other.fmid)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FinancialManager [fmid=" + fmid + "]";
	}

	
}
