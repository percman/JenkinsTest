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

//@WebServlet(value="/EagerServlet")
@WebServlet(urlPatterns= {"/EagerServlet", "/AlsoEagerServlet"}, name="EagerServlet", loadOnStartup=1)
public class EagerServlet extends HttpServlet {

	private static final long serialVersionUID = 1360774492111558797L;

	public void init() {
		System.out.println("EagerServlet.init() method called");
	}

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("EagerService.service() method called");
		ServletContext context = getServletContext();
		ServletConfig config = getServletConfig();
		
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		pw.append("<html><body>");
		pw.append("<p>Called from EargerServlet.service() method</p>");
		pw.append("<p>The value of the Context Param in => " + context.getInitParameter("anyServlet") + "</p>");
		pw.append("<p>The value of the Init Param in => " + config.getInitParameter("lazy") + "</p>");
		pw.append("</body></html>");

		request.getRequestDispatcher("/index.jsp").include(request, response);
		pw.close();
	}

	public void destroy() {
		System.out.println("EagerService.destroy() method called");
	}
}
