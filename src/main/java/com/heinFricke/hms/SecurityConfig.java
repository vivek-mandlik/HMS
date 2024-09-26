package com.heinFricke.hms;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/h2-console/**").permitAll() // Allow access to H2 console
				.antMatchers("/**").permitAll() // Allow access to all other endpoints (for testing)
				.and().csrf().disable() // Disable CSRF for H2 console and all requests
				.headers().frameOptions().sameOrigin(); // Allow H2 console to be loaded in a frame
	}
}