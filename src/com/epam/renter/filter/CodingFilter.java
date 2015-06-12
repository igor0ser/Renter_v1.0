package com.epam.renter.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//@WebFilter("/CodingFilter")
public class CodingFilter implements Filter {
	private final Logger logger = LogManager.getLogger(CodingFilter.class
			.getName());
	private static final String CODING = "UTF-8";

	public CodingFilter() {
	}

	public void destroy() {
	}

// Filter changes coding to UTF-8
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		if (request.getCharacterEncoding() != CODING) {
			request.setCharacterEncoding(CODING);
			logger.trace("Request coding changed to UTF-8");
		}
		if (response.getCharacterEncoding()!=CODING){
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			logger.trace("Responce coding changed to UTF-8");
		}
			chain.doFilter(request, response);
	}


	public void init(FilterConfig fConfig) throws ServletException {
	}

}
