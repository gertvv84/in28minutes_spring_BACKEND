package com.in28minutes.rest.webservices.restfulwebservices.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
public class BasicAuthenticationSecurityConfiguration {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// Authenticate all requests
		// + requestMatchers (in older Spring: antMatchers)
		// --> If an OPTIONS requests comes in: to an of the url's
		http.authorizeHttpRequests(
				auth -> auth.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll().anyRequest().authenticated());
		// Shows pop-up for basic authentication
		http.httpBasic(Customizer.withDefaults());
		// Stateless
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		// Disable csrf
		http.csrf(csrf -> csrf.disable());

		return http.build();
	}

}
