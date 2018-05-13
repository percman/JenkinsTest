package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.logging.InvalidLoginException;
import com.revature.logging.LogThis;
import com.revature.model.Employee;
import com.revature.model.LoginFactory;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/x-www-form-urlencoded");

		ObjectMapper mapper = new ObjectMapper();

		String json = request.getReader().readLine();
		LogThis.info("json in authenticate: " + json);

		Employee temp = mapper.readValue(json, Employee.class);
		LogThis.info("temp in authenticate: " + temp);

		Employee currentEmployee;
		try {
			currentEmployee = LoginFactory.userLogin(temp);
		} catch (InvalidLoginException e) {
			request.getRequestDispatcher("404.do").include(request, response);
			return;
		}

		LogThis.info("currentEmployee in authenticate: " + currentEmployee);

		if (currentEmployee.isFinancialManager()) {
			request.getSession().setAttribute("currentEmployee", currentEmployee);
			request.getRequestDispatcher("finManHome.do").include(request, response);
		} else if (!currentEmployee.isFinancialManager()) {
			request.getSession().setAttribute("currentEmployee", currentEmployee);
			request.getRequestDispatcher("employeeHome.do").include(request, response);
		} else {
			request.getRequestDispatcher("404.do").include(request, response);
		}
	}

}
