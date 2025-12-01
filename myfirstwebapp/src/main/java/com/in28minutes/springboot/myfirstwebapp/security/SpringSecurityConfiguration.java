package com.in28minutes.springboot.myfirstwebapp.security;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {

	@Bean
	public InMemoryUserDetailsManager crateUserDetailsManager() {
		Function<String, String> pswdEncoder = input -> this.passwordEncoder().encode(input);
		UserDetails userDetails1 = createUser(pswdEncoder, "GVV", "dummy");
		UserDetails userDetails2 = createUser(pswdEncoder, "Kobe", "dummy");

		return new InMemoryUserDetailsManager(userDetails1, userDetails2);
	}

	private UserDetails createUser(Function<String, String> pswdEncoder, String username, String password) {
		UserDetails userDetails = User.builder().passwordEncoder(pswdEncoder).username(username).password(password)
				.roles("USER", "ADMIN").build();
		return userDetails;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSec) throws Exception {
		httpSec.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
		httpSec.formLogin(withDefaults());

//		Deprecated since 6.1 :
//		httpSec.csrf().disable();
//		httpSec.headers().frameOptions().disable();

		// X-Frame-Optionas enabled ==> Frames cannot be used.
		// h2-console uses frames ==> Disable X-Frame-Options header
		httpSec.csrf(csrf -> csrf.disable());
		httpSec.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));

		return httpSec.build();

	}

}
