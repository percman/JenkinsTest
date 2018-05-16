package com.revature.model;

public class Manager extends Employee{

	private static final long serialVersionUID = 3639210872307779061L;
	private int managerId;
	public Manager() {}
	public Manager(int id, String username, String password, String firstname, char middleInit, String lastName, int managerId) {
		super(id, username, password, firstname, middleInit, lastName);
		this.managerId=managerId;
	}
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + managerId;
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
		Manager other = (Manager) obj;
		if (managerId != other.managerId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Manager [managerId=" + managerId + "]";
	}



}
