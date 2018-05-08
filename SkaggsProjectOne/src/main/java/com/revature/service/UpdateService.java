package com.revature.service;

import javax.servlet.http.HttpServletRequest;

public class UpdateService {
	private UpdateService() {}
	
	public static String update(HttpServletRequest request, String updateType) {
		return "/update.jsp";
	}
}
