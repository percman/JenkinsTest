package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloWorldServlet extends HttpServlet {

	private static final long serialVersionUID = -8313834183301309288L;

	public HelloWorldServlet() {
		super();
	}
	
	public void init() {
		System.out.println("Inside the HelloWorldServlet.init() method");
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside the HelloWorldServlet.service() method");
	}
	
	public void destroy() {
		System.out.println("Inside the HelloWorldServlet.destroy() method");
	}
}
