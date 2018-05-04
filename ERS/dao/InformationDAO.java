package com.revature.dao;

import com.revature.model.Employee;

public interface InformationDAO {
	Employee getInformation(int id);
	void setInformation(Employee employee);
}
