package com.revature.dao;

import java.util.List;

import org.apache.log4j.Logger;

import com.revature.model.Employee;
import com.revature.model.Reimbursement;

public class EmployeeDaoImpl implements EmployeeDao{

	private static final Logger logger = Logger.getLogger(EmployeeDaoImpl.class);

	private static EmployeeDaoImpl instance;
	private EmployeeDaoImpl() {}
	public static EmployeeDaoImpl getInstance() {
		if(instance == null) {
			instance = new EmployeeDaoImpl();
		}
		return instance;
	}
	@Override
	public boolean insertEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean viewEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public List<Reimbursement> listPending(Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reimbursement> listResolved(Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}
}
