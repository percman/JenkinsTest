package com.github.jochenw.clacksov;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;


public class ClacksOverheadFilter implements Filter {
	public void init(FilterConfig pConfig) throws ServletException {
		// Do nothing.
	}
	public void destroy() {
		// Nothing to do.
	}

	public void doFilter(ServletRequest pReq, ServletResponse pRes, FilterChain pChain) throws IOException,
			ServletException {
		if (pRes instanceof HttpServletResponse) {
			final HttpServletResponse res = (HttpServletResponse) pRes;
			res.addHeader("X-Clacks-Overhead", "GNU-Terry-Pratchett");
		}
		pChain.doFilter(pReq, pRes);
	}
}
