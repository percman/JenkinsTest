package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns= {"/EagerServlet", "/AlsoEagerServlet"}, 
name="EagerServlet", loadOnStartup=1)
public class EagerServlet extends HttpServlet {

	private static final long serialVersionUID = 1360774492111558797L;

	public void init() {
		System.out.println("EagerServlet.init() method called");
	}
	
	public void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("EagerServlet.service() method called");
		
		// Create an object of type ServletContext
		ServletContext context = getServletContext();
		
		// Create an object of type ServletConfig
		ServletConfig config = getServletConfig();
		
		PrintWriter pw = response.getWriter();
		pw.append("<html><body>");
		pw.append("<p>Called from the EagerServlet.service() method</p>");
		pw.append("<p>The value of the Context Param is => " + context.getInitParameter("anyServlet") + "</p>");
		pw.append("<p>The value of the Init Param is => " + config.getInitParameter("lazy") + "</p>");
		pw.append("</body></html>");
		
		request.getRequestDispatcher("index.jsp").include(request, response);
		
		// Close our resources
		pw.close();
	}
	
	public void destroy() {
		System.out.println("EagerServlet.destroy() method called");
	}
}
