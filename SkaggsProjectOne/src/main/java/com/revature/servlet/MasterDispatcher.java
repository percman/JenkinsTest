package com.revature.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.service.LoginService;
import com.revature.service.RequestByEmployee;
import com.revature.service.RequestService;
import com.revature.service.UpdateRequestService;
import com.revature.service.UpdateService;
import com.revature.service.UserService;

public class MasterDispatcher {

	public static String process(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, IOException, ServletException {
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
			
		case "/SkaggsProjectOne/request-update.do":
			return  RequestByEmployee.request(request);
			
		default:
			return "404.jsp";
		}
	}
}
