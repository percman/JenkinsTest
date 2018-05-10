package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LazyServlet extends HttpServlet {

	private static final long serialVersionUID = 1864920680602651810L;

	public void init() {
		System.out.println("LazyServlet.init() method called");
	}
	
	public void service(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		System.out.println("LazyServlet.service() method called");
		
		ServletContext context = getServletContext();
		ServletConfig config = getServletConfig();
		
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		pw.append("<html><body>");
		pw.append("<p>Called from LazyServlet.service() method</p>");
		pw.append("<p>The value of the Context Param is => " + context.getInitParameter("anyServlet") + "</p>");
		pw.append("<p>The value of the Init Param is => " + config.getInitParameter("lazy") + "</p>");
		pw.append("</body></html>");
	
		request.getRequestDispatcher("index.jsp").include(request, response);
		// Close your resources
		pw.close();
	}
	
	public void destroy() {
		System.out.println("LazyServlet.destroy() method called");
	}
}











