package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.logging.LogThis;


public class MasterServlet extends HttpServlet {
   
	private static final long serialVersionUID = 6520317557229175093L;

	public MasterServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(MasterDispatcher.process(request, response)).forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
//
////		LogThis.info("MasterServlet request.getReader.readLine: " + request.getReader().readLine());
//		response.setContentType("application/json");
//		
//		// Get a reference to the ObjectMapper
//		ObjectMapper mapper = new ObjectMapper();
//		
//		//LogThis.info(" mapper:  " + mapper.writeValueAsString(MasterDispatcher.process(request, response)));
//		// Write the POJO as JSON to the response
//		response.getWriter().write(
//				mapper.writeValueAsString(MasterDispatcher.process(request, response)));


	}

}
