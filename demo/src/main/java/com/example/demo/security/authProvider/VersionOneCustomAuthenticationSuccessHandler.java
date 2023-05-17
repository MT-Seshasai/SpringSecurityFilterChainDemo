package com.example.demo.security.authProvider;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class VersionOneCustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
Logger log = Logger.getLogger(this.getClass().getSimpleName());
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		log.log(Level.INFO, this.getClass().getSimpleName()+ " is called when authentication success");
	}

}
