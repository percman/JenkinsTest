package com.revature.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import com.revature.dao.Employee;
import com.revature.dao.EmployeeService;
import com.revature.reimbursement.Reimbursement;

@MultipartConfig
public class RequestService {

	public static String reqest(HttpServletRequest request) throws ClassNotFoundException, IOException, ServletException {
		
		System.out.println("We're in the request");
		String requestType = getValue(request.getPart("requestType"));
		int amount = Integer.parseInt(getValue(request.getPart("amount")));
		Employee employee = (Employee) request.getSession().getAttribute("authorizedUser");

		InputStream input = null;
		Part file =  request.getPart("uploadImg");

		input = file.getInputStream();
		Reimbursement r = new Reimbursement(0, employee.getEmployeeId(), 0, requestType, 0, 
				amount, "", "", "", "", "", "", input);
		EmployeeService.insertRequest(r);
		if (employee.isFinanceManager()) {
			return "./makeRequestFM.jsp";
		}
		else {
			return "/home.jsp";
		}
	}
	private static String getValue(Part part) throws IOException {
	    BufferedReader reader = new BufferedReader(new InputStreamReader(part.getInputStream(), "UTF-8"));
	    StringBuilder value = new StringBuilder();
	    char[] buffer = new char[1024];
	    for (int length = 0; (length = reader.read(buffer)) > 0;) {
	        value.append(buffer, 0, length);
	    }
	    return value.toString();
	}

}
