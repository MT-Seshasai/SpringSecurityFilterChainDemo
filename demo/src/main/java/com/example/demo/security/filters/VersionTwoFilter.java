package com.example.demo.security.filters;

import java.io.IOException;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class VersionTwoFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		var httpServletRequest = (HttpServletRequest) request;
		var httpServletResponse = (HttpServletResponse) response;
		
		String uname = httpServletRequest.getHeader("unameTwo");
		String password = httpServletRequest.getHeader("passwordTwo");
		
		if(StringUtils.hasLength(uname) && StringUtils.hasLength(password)) {
			
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(uname, password);
			SecurityContext context = SecurityContextHolder.createEmptyContext();
			context.setAuthentication(token);
			SecurityContextHolder.setContext(context);
			
		}else {
			throw new BadCredentialsException("No credential present here");
		}
		
		chain.doFilter(request, response);

	}

}
