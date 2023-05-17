package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.security.authProvider.VersionOneCustomAuthenticationSuccessHandler;
import com.example.demo.security.authProvider.VersionOneProvider;
import com.example.demo.security.authProvider.VersionTwoProvider;
import com.example.demo.security.filters.VersionOneFilter;
import com.example.demo.security.filters.VersionTwoFilter;

@Configuration
public class SecurityConfig {

	@Bean
	@Order(1)
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		AuthenticationManager authManager = localVersionOneAuthManager(httpSecurity);// httpSecurity.getSharedObject(AuthenticationManager.class);

		VersionOneCustomAuthenticationSuccessHandler successHandler = new VersionOneCustomAuthenticationSuccessHandler();

		VersionOneFilter filter = new VersionOneFilter("/v1/**", authManager);
//		filter.setAuthenticationSuccessHandler(successHandler);

		httpSecurity.securityMatcher("/v1/**");
		httpSecurity.csrf().disable().authorizeHttpRequests().requestMatchers("/v1/**").permitAll().anyRequest()
				.authenticated().and().addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
				.authenticationManager(authManager).formLogin().disable().sessionManagement(session -> {
					session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
				});
		return httpSecurity.build();
	}

	@Bean
	@Order(2)
	public SecurityFilterChain filterChainForVersionTwo(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.securityMatchers(requestMatcher -> requestMatcher.requestMatchers("/v2/**"));
		httpSecurity.csrf().disable().authorizeHttpRequests().requestMatchers("/v2/**").permitAll().and()
				.addFilterBefore(new VersionTwoFilter(), UsernamePasswordAuthenticationFilter.class).formLogin()
				.disable().authenticationManager(localVersionTwoAuthManager(httpSecurity));
		return httpSecurity.build();
	}

	@Bean
	@Order(3)
	public SecurityFilterChain filterChainForDefault(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.build();
	}

	public AuthenticationManager localVersionOneAuthManager(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
		builder.authenticationProvider(new VersionOneProvider());
		return builder.build();
	}

	public AuthenticationManager localVersionTwoAuthManager(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
		builder.authenticationProvider(new VersionTwoProvider());
		return builder.build();
	}
}
