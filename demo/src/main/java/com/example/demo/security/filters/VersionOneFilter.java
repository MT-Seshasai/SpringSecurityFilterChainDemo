package com.example.demo.security.filters;

import java.io.IOException;
import java.util.Collections;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.util.StringUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class VersionOneFilter extends AbstractAuthenticationProcessingFilter {

	public VersionOneFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager) {
		super(defaultFilterProcessesUrl);
		this.setAuthenticationManager(authenticationManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		// TODO Auto-generated method stub
		
		var httpServletRequest = (HttpServletRequest) request;
		var httpServletResponse = (HttpServletResponse) response;
		
		String uname = httpServletRequest.getHeader("uname");
		String password = httpServletRequest.getHeader("password");
		
		if(StringUtils.hasLength(uname) && StringUtils.hasLength(password)) {
			
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(uname, password);
			Authentication authentication = getAuthenticationManager().authenticate(token);
			return authentication;
			
		}else {
			throw new BadCredentialsException("No credential present here");
		}
	}	
	
	
	@Override
	public void setAuthenticationSuccessHandler(AuthenticationSuccessHandler successHandler) {
		// TODO Auto-generated method stub
		super.setAuthenticationSuccessHandler(successHandler);
	}
	
	
	
}
