package com.revature.manager;

import com.revature.employee.Employee;

public class Manager extends Employee {
	
	int manager_id;
	
	public Manager() {}

	public Manager(int manager_id) {
		super();
		this.manager_id = manager_id;
	}

	public int getManager_id() {
		return manager_id;
	}

	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + manager_id;
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
		if (manager_id != other.manager_id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Manager [manager_id=" + manager_id + "]\n" + super.toString();
	}
}
