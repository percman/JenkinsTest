package com.revature.servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CorsFilter implements Filter {
	
	/*
	 * If you check the web.xml, I initially set this CorsFilter to filter requests for any url-mapping. 
	 * This can be modified to fit your needs
	 */

    public CorsFilter() {}

	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// Cast the ServletRequest to an HttpServletRequest in order to get the HTTP Method attached with the request
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		System.out.println("CORSFilter HTTP Request: " + httpRequest.getMethod());
		System.out.println("CORSFilter request from " + httpRequest.getRemoteHost());
		System.out.println("CORSFilter request from " + httpRequest.getRemoteAddr());
		System.out.println("CORSFilter request from " + httpRequest.getRemotePort());

		// Authorize domain(s) to consume the content
		/*
		 * I added the domain for the default port of an Angular application
		 * More domains can be added in the second argument of the Access-Control-Allow-Origin setHeader() method
		 * Have the additional domains in a comma separated list
		 */
		((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
		((HttpServletResponse) response).addHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
		// Authorize the HTTP methods from which you allow different domains to access your resources
		((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");
		
		// Cast the ServletResponse to an HttpServletResponse to set a Status Code
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		// For the HTTP OPTIONS method reply with ACCEPTED status code - per the CORS handshake
		if (httpRequest.getMethod().equals("OPTIONS")) {
			// HttpServletResponse.SC_ACCEPTED is the 202 HTTP Status Code
			httpResponse.setStatus(200);
			return;
		}
		
		// Any additional code can be placed here

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}


	public void init(FilterConfig fConfig) throws ServletException {}

}
