package com.revature.dao;

import java.util.List;

import com.revature.employee.FinanceManager;
import com.revature.exceptions.EmployeeNotFoundException;

public class ManagerService {
private static ManagerDao dao = ManagerDaoImpl.getInstance();

public static boolean addManager(FinanceManager man) {
	return dao.addManager(man);
}

public static List<FinanceManager> getManagers(){
	return dao.getManagers();
}

public static FinanceManager getManager(String man) throws EmployeeNotFoundException{
	return dao.getManager(man);
}

public static boolean updateInfo(FinanceManager man) throws EmployeeNotFoundException{
	return dao.updateInfo(man);
}

public static String getPasswordHash(FinanceManager man) {
	return dao.getPasswordHash(man);
}

public static void main(String[] args) throws EmployeeNotFoundException {
	System.out.println(getManagers().get(0).getUsername());
	System.out.println(getManager("aksjflk").getFirstName());
}

}
