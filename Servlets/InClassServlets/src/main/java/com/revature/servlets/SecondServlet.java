package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecondServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SecondServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		pw.write("SecondServlet - GET");
		response.setContentType("text/html");
		pw.write("<html><body>");
		String getInput = request.getParameter("getInput");
		pw.write("<p>You wrote: " + getInput + "</p>");
		pw.write("</body></html>");
		
		request.getRequestDispatcher("index.jsp").include(request, response);
		pw.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		pw.write("SecondServlet - POST");
		response.setContentType("text/html");
		pw.write("<html></body>");
		String postInput = request.getParameter("postInput");
		pw.write("<p>You wrote: " + postInput + "</p>");
		pw.write("</body></html>");
		request.getRequestDispatcher("index.jsp").include(request, response);
		pw.close();
	}
	
	
	
	
	
	
	
	
	

}
