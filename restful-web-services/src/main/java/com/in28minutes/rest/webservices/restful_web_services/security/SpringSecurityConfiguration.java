package com.in28minutes.rest.webservices.restful_web_services.security;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		1) All requests should be authenticated
//		This will cause a 403 response to be returned when not authenticated request		
		http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());

//		2) If request not authenticated, a web page (login form) is shown
		http.httpBasic(withDefaults());

//		3) CSRF --> POST, PUT
		http.csrf(csrf -> csrf.disable());

		return http.build();
	}

}
