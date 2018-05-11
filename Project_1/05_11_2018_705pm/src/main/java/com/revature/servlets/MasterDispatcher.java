package com.revature.servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.servletServices.LoginServletService;
import com.revature.servletServices.ManagerServletService;
import com.revature.servletServices.ReimbursementServletService;
import com.revature.servletServices.UserServletService;

public class MasterDispatcher {

	private MasterDispatcher() {
	}

	public static String process(HttpServletRequest request, HttpServletResponse response) {

		switch (request.getRequestURI()) {

		case "/Project_1/update.do": return UserServletService.updateUser(request, response);
		case "/Project_1/submitRequest.do": return ReimbursementServletService.submit(request, response);
		case "/Project_1/login.do": return LoginServletService.login(request, response);
		case "/Project_1/home.do": return UserServletService.home(request, response);
		case "/Project_1/managerHome.do": return ManagerServletService.home(request, response);
		case "/Project_1/account.do": return UserServletService.account(request, response);
		case "/Project_1/request.do": return UserServletService.request(request, response);
		case "/Project_1/popupApproveReimbursement.do": return ReimbursementServletService.approve(request, response);
		case "/Project_1/managerManage.do": return ManagerServletService.manage(request, response);
		default:{
			System.out.println("bad");
			return "404.jsp";
		}			
		}
	}
}
