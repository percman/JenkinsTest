package com.revature.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.revature.dao.EmployeeService;
import com.revature.dao.ManagerService;
import com.revature.employee.FinanceManager;
import com.revature.employee.GenericEmployee;
import com.revature.exceptions.EmployeeNotFoundException;


public class LoginService {
	private static final Logger logger = Logger.getLogger(LoginService.class);

		private LoginService() {}
		
		public static String login(HttpServletRequest request, HttpServletResponse responce) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			//Login logic
			try {
				FinanceManager man = ManagerService.getManager(username);
				String pass = ManagerService.getPasswordHash(new FinanceManager(username,password));
				if(man.getPassword().equals(ManagerService.getPasswordHash(new FinanceManager(username,password)))) {
					FinanceManager authorized = man;
					request.getSession().setAttribute("authorizedUser", authorized);
					return "/managerHome.do";
				}
			} catch (EmployeeNotFoundException enfe) {
				try {
					GenericEmployee emp = EmployeeService.getEmployee(username);
					System.out.println(emp.getPassword());
					System.out.println(EmployeeService.getPasswordHash(new GenericEmployee(username,password)));
					if(emp.getPassword().equals(EmployeeService.getPasswordHash(new GenericEmployee(username,password)))) {
						GenericEmployee authorized = emp;
						request.getSession().setAttribute("authorizedUser", authorized);
						return "/employeeHome.do";
					}
					}catch (EmployeeNotFoundException enfe2) {
				logger.error(enfe2.getMessage());
					}
			}
			
			return "index.jsp";
		}
		public static String logout(HttpServletRequest request, HttpServletResponse responce) {
			request.getSession().setAttribute("authorizedUser", new GenericEmployee());
			return "index.jsp";
		}
	}
