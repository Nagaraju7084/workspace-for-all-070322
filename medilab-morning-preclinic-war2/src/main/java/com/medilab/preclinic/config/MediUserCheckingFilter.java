package com.medilab.preclinic.config;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Servlet Filter implementation class MediUserCheckingFilter
 */
//@WebFilter("/MediUserCheckingFilter")
public class MediUserCheckingFilter extends OncePerRequestFilter {
       
    /**
     * @see OncePerRequestFilter#OncePerRequestFilter()
     */
    public MediUserCheckingFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("i am from mediusercheckingfilter");
		System.out.println("user name is:\t"+request.getParameter("username"));
		System.out.println("password is:\t"+request.getParameter("password"));
		
		System.out.println(request.getHeader("Authorization"));
		
		filterChain.doFilter(request, response);
	}

}
