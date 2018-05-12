package com.revature.servlets;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class HelloWorldFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		System.out.println("Inside the HelloWorldFilter.doFilter() method");
		System.out.println("HTTP Request: " + httpRequest.getMethod());
		
		Enumeration<String> headers = httpRequest.getHeaderNames();
		System.out.println("\nHeaders\n========================");
		while(headers.hasMoreElements()) {
			String header = headers.nextElement();
			System.out.println(header + " => " + httpRequest.getHeader(header));
		}
		System.out.println();
		
		Enumeration<String> params = httpRequest.getParameterNames();
		System.out.println("\nParameters\n=================");
		while(params.hasMoreElements()) {
			String param = params.nextElement();
			System.out.println(param + " => " + httpRequest.getParameter(param));
		}
		
		System.out.println();
		System.out.println();
		
		// Finally, we pass the request along the filter chain
		chain.doFilter(httpRequest, response);
	}
	
	public void destroy() {}
	
	public void init(FilterConfig fConfig) throws ServletException {}

}











