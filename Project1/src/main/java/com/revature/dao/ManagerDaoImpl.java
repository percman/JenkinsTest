package com.revature.dao;

import java.util.List;

import org.apache.log4j.Logger;

import com.revature.model.Employee;
import com.revature.model.Manager;
import com.revature.model.Reimbursement;

public class ManagerDaoImpl implements ManagerDao {

	private static final Logger logger = Logger.getLogger(ManagerDaoImpl.class);

	private static ManagerDaoImpl instance;
	private ManagerDaoImpl() {}
	public static ManagerDaoImpl getInstance() {
		if(instance == null) {
			instance = new ManagerDaoImpl();
		}
		return instance;
	}
	@Override
	public boolean insertManager(Manager manager) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean approveDeny(String response, int reimburseId, int managerId) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public List<Employee> viewEmployees() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Reimbursement> viewReimbursements() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Reimbursement> viewReimbursementByEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Manager approver(Reimbursement reimbursement) {
		// TODO Auto-generated method stub
		return null;
	}

}
