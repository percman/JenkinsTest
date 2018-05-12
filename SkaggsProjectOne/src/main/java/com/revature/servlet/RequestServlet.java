package com.revature.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;


public class RequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public RequestServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json");
		
		// Get a reference to an ObjectMapper to read the JSON
		ObjectMapper mapper = new ObjectMapper();
		String json = request.getReader().readLine();
		
		// Added this print statement
		System.out.println("json string " + json);
		request.getRequestDispatcher("requestEmployee.jsp").forward(request, response);
	}

}
