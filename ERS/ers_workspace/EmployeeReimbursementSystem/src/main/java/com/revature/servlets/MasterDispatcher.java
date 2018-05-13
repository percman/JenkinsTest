package com.revature.servlets;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.logging.InvalidLoginException;
import com.revature.logging.LogThis;
import com.revature.model.Employee;
import com.revature.model.LoginFactory;
import com.revature.service.EmployeeService;

public class MasterDispatcher {

	private MasterDispatcher() {
	}

	public static String process(HttpServletRequest request, HttpServletResponse response) {
		ObjectMapper mapper = new ObjectMapper();

		switch (request.getRequestURI()) {
		case "/EmployeeReimbursementSystem/isFinMan":

			try {
				String json = request.getReader().readLine();
				LogThis.info("isFinMan : " + json);
				Employee temp = mapper.readValue(json, Employee.class);
//						.replace("[\"", "").replaceAll("\"]", "");
				LogThis.info("employee in is fin man : " + temp.toString());
				if (EmployeeService.isFinMan(temp)) {
					LogThis.info("Returning true from /isFinMan");
					return "true";
				} else {
					LogThis.info("Returning false from /isFinMan");
					return "false";
				}
			} catch (InvalidLoginException ile) {
				LogThis.warn(ile.getMessage());
			} catch (IOException ioe) {
				LogThis.warn(ioe.getMessage());
			}
		case "/EmployeeReimbursementSystem/login":
			return null;
		default:
			LogThis.info("The request URI was: " + request.getRequestURI());
			LogThis.info("Returning 404 from default");
			return "404.jsp";

		}

	}

}
