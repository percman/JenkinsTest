package com.revature.model;

import java.io.Serializable;

public class Employee implements Serializable {

	private static final long serialVersionUID = 861925046890513138L;

	// These are in the employee table
	private int id;
	private String username;
	private String password;
	private boolean isFinancialManager;

	// These are in the employee info table
	private String firstname;
	private String middleInitial;
	private String lastname;
	private String phone;
	private String email;

	// Public no-arg constructor
	public Employee() {
	}

	public Employee(String username) {
		super();
		this.username = username;
	}

	// Public constructor that takes a username and password
	public Employee(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public Employee(String firstname, String middleInitial, String lastname) {
		super();
		this.firstname = firstname;
		this.middleInitial = middleInitial;
		this.lastname = lastname;
	}

	// Public constructor that takes all fields
	public Employee(int id, String username, String password, boolean isFinancialManager, String firstname,
			String middleInitial, String lastname, String phone, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.isFinancialManager = isFinancialManager;
		this.firstname = firstname;
		this.middleInitial = middleInitial;
		this.lastname = lastname;
		this.phone = phone;
		this.email = email;
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

	public boolean isFinancialManager() {
		return isFinancialManager;
	}

	public void setFinancialManager(boolean isFinancialManager) {
		this.isFinancialManager = isFinancialManager;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getMiddleInitial() {
		return middleInitial;
	}

	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + id;
		result = prime * result + (isFinancialManager ? 1231 : 1237);
		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
		result = prime * result + ((middleInitial == null) ? 0 : middleInitial.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phone == null) ? 0 : lastname.hashCode());
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
		Employee other = (Employee) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (id != other.id)
			return false;
		if (isFinancialManager != other.isFinancialManager)
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		if (middleInitial == null) {
			if (other.middleInitial != null)
				return false;
		} else if (!middleInitial.equals(other.middleInitial))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phone != other.phone)
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
		return "Employee [id=" + id + ", username=" + username + ", password=" + password + ", isFinancialManager="
				+ isFinancialManager + ", firstname=" + firstname + ", middleInitial=" + middleInitial + ", lastname="
				+ lastname + ", phone=" + phone + ", email=" + email + "]";
	}


}
