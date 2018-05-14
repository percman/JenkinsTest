package com.revature.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.ManagerDao;
import com.revature.dao.ManagerDaoImpl;
import com.revature.model.Employee;
import com.revature.model.Manager;
import com.revature.model.Reimbursement;

public class ManagerService {
	private ManagerService() {}
	private static ManagerDao dao = ManagerDaoImpl.getInstance();
	
	public static boolean insertManager(Manager manager){
		return dao.insertManager(manager);
	}
	
	public static String approveDeny(HttpServletRequest request, HttpServletResponse response) {
		Manager manager=(Manager)request.getSession().getAttribute("authorizedManager");
		if(dao.approveDeny(request.getParameter("status"), Integer.parseInt(request.getParameter("reimburse_id")), 
				manager.getManagerId())) {
			return "/Manager.jsp";
		}
		else return "/ManagerPending.do";
	}
	
	public static String viewEmployees(HttpServletRequest request, HttpServletResponse response){
		List<Employee> employees =dao.viewEmployees();
		request.setAttribute("employeeList", employees);
		return "/ViewEmployees.jsp";
	}
	
	public static String viewReimbursements(HttpServletRequest request, HttpServletResponse response){
		List<Reimbursement> reimbursements= dao.viewReimbursements();
		request.setAttribute("reimbursementList", reimbursements);
		return "/AllReimbursements.jsp";
	}
	
	public static String viewReimbursementByEmployee(HttpServletRequest request, HttpServletResponse response){
		List<Reimbursement> reimbursements=dao.viewReimbursementByEmployee(Integer.parseInt(request.getParameter("employee_id")));
		request.setAttribute("reimbursementList", reimbursements);
		request.setAttribute("employeeName", EmployeeService.getName(Integer.parseInt(request.getParameter("employee_id"))));
		return "/EmployeeReimbursements.jsp";
	}
	
	public static String approver(int id) {
		return dao.approver(id);
	}
	
	public static String listPending(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("pendingList", dao.listPending());
		return "/ManagerPending.jsp";
	}
}
