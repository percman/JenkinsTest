package com.revature.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.LoginDao;
import com.revature.dao.LoginDaoImpl;
import com.revature.model.Employee;
import com.revature.model.User;

public class LoginService {
	private LoginService() {}
	private static LoginDao dao = LoginDaoImpl.getInstance();
	
	public static String getPassword(String username) {
		return dao.getPassword(username);
	}
	public static String getPasswordHash(User user) {
		return dao.getPasswordHash(user);
	}
	
	public static String login(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if(EmployeeService.getEmployee(username)!=null){
			if(getPassword(username).equals(getPasswordHash(new Employee(username, password)))) {
				User authorizedUser=EmployeeService.getEmployee(username);
				request.getSession().setAttribute("authorizedUser", authorizedUser);
				if(authorizedUser.getClass().getName().equals("com.revature.model.Manager")) {
					request.getSession().setAttribute("authorizedManager", authorizedUser);
					return "/Manager.jsp";
				}else return "/employee.jsp";
			}
		}
		return "/index.html";
	}
	
	public static String logout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute("authorizedUser");
		request.getSession().removeAttribute("authorizedManager");
		request.getSession().invalidate();
		return "/index.jsp";
	}
}
