package com.example.demo.security.authProvider;

import java.util.Collections;
import java.util.Objects;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.util.Assert;

public class VersionOneProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
//		Assert.(authentication);
		if (Objects.nonNull(authentication))
			if (authentication.getPrincipal().equals("test") && authentication.getCredentials().equals("test")) {
//				authentication.setAuthenticated(true);
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), Collections.emptyList() );
				return authToken;
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
