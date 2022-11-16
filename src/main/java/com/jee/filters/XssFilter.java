package com.jee.filters;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequestWrapper;


@WebFilter("/XssFilter")
public class XssFilter extends HttpFilter implements Filter {
       
	public void destroy() {

	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		chain.doFilter(request, response);
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
	
	private static class XssResquestWrapper extends HttpServletRequestWrapper{
		
		public XssRequestWrapper(HttpServletRequest request) {
			super(request);
		}
		
		private static final Pattern [] XSS_PATTERNS = {
				
		};
	}

}
