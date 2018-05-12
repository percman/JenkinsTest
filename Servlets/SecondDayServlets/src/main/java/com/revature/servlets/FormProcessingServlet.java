package com.revature.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FormProcessingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public FormProcessingServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside the FormProcessingServlet.doGet() method");
		
		// Get the text from the form 
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String contact = request.getParameter("contact");
		
		System.out.println("The value for the username input => " + username);
		System.out.println("The value for the password input => " + password);
		System.out.println("The value for the contact input => " + contact);
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
