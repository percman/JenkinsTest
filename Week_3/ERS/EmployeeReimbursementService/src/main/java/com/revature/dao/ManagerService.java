package com.revature.dao;

import java.util.List;

import com.revature.employee.FinanceManager;
import com.revature.exceptions.EmployeeNotFoundException;

public class ManagerService {
private ManagerDao dao = ManagerDaoImpl.getInstance();

public boolean addManager(FinanceManager man) {
	return dao.addManager(man);
}

public List<FinanceManager> getManagers(){
	return dao.getManagers();
}

public FinanceManager getManager(String man) throws EmployeeNotFoundException{
	return dao.getManager(man);
}

public boolean updateInfo(FinanceManager man) throws EmployeeNotFoundException{
	return dao.updateInfo(man);
}

String getPasswordHash(FinanceManager man) {
	return dao.getPasswordHash(man);
}
}
