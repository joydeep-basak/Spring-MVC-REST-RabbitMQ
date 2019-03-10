package com.joydeep.springmvc.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component("loggingFilter")
public class RequestLoggingFilter implements Filter {
 
	private static final Logger logger = Logger.getLogger(RequestLoggingFilter.class);
 
    @Override
    public void init(FilterConfig config) throws ServletException {
        // initialize something
    }
 
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
  
        HttpServletRequest req = (HttpServletRequest) request;
        logger.info("Request Info : " + req.getRequestURI());
        if (req.getRequestURI().replace("/Spring5WebMvcSecurity/", "").trim().equals("")) {
        	req.getSession(false);
        } else {
        	req.getSession(true);
        }
        chain.doFilter(request, response);
    }
 
    @Override
    public void destroy() {
        // cleanup code, if necessary
    }
}