package com.blogapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.blogapp.security.CustomUserDetailsService;
import com.blogapp.security.JWTAuthenticationEntryPoint;
import com.blogapp.security.JWTAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JWTAuthenticationEntryPoint authenticationEntryPoint;

	@Autowired
	private JWTAuthenticationFilter authenticationFilter;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	//STEP-6 Configure JWT in Spring Security - Added new implementation in configure method
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeHttpRequests()
		.antMatchers("/api/v1/auth/login")
		.permitAll().anyRequest().authenticated().and() // means anytime any
																							// unauthorized user will
																							// try to access api then
																							// exception will be thrown
																							// and then this will tell
																							// the entrypoint class.
				.exceptionHandling().authenticationEntryPoint(this.authenticationEntryPoint).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(this.authenticationFilter, UsernamePasswordAuthenticationFilter.class);

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	

}
