package com.revature.dao;

public class Employee {
	int employeeId;
	String firstName;
	String middleInit;
	String lastName;
	String userName;
	String password;
	boolean isFinanceManager =  false;
	
	public Employee(String firstName, String middleInit, String lastName, String userName, String password, boolean isFinanceManager) {
		super();
		this.firstName = firstName;
		this.middleInit = middleInit;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.isFinanceManager = isFinanceManager;
	}
	public Employee(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public boolean isFinanceManager() {
		return isFinanceManager;
	}
	public void setFinanceManager(boolean isFinanceManager) {
		this.isFinanceManager = isFinanceManager;
	}
	public Employee() {}

	
	public Employee(int employeeId, String firstName, String middleInit, String lastName, String userName,
			String password, boolean isFinanceManager) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.middleInit = middleInit;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.isFinanceManager = isFinanceManager;
	}
	@Override
	public String toString() {
		return "Employee [firstName=" + firstName + ", middleInit=" + middleInit + ", lastName=" + lastName
				+ ", userName=" + userName + ", password=" + password + "]";
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleInit() {
		return middleInit;
	}
	public void setMiddleInit(String middleInit) {
		this.middleInit = middleInit;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
}
