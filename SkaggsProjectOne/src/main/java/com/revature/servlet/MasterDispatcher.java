package com.revature.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.Employee;
import com.revature.dao.EmployeeService;
import com.revature.service.LoginService;
import com.revature.service.RequestService;
import com.revature.service.UpdateRequestService;
import com.revature.service.UpdateService;
import com.revature.service.UserService;

public class MasterDispatcher {

	public static String process(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException {
		System.out.println("In master dispatcher");
		switch(request.getRequestURI()) {
		case "/SkaggsProjectOne/login.do":
			return LoginService.login(request);
		case "/SkaggsProjectOne/home.do":
			return UserService.home(request);
		case "/SkaggsProjectOne/fm.do":
			return UserService.fm(request);
		case "/SkaggsProjectOne/update.do":
			return UpdateService.update(request);
		case "/SkaggsProjectOne/update-request.do":
			return UpdateRequestService.update(request);
		case "/SkaggsProjectOne/request.do":
			return  RequestService.reqest(request);
		default:
			return "404.jsp";
		}
	}
}
