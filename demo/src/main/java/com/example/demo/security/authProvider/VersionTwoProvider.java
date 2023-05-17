package com.example.demo.security.authProvider;

import java.util.Objects;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class VersionTwoProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		if (Objects.nonNull(authentication))
			if (authentication.getPrincipal().equals("test2") && authentication.getCredentials().equals("test2")) {
				authentication.setAuthenticated(true);
				return authentication;
			} else {
				throw new BadCredentialsException("Invalid credentials");
			}
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
