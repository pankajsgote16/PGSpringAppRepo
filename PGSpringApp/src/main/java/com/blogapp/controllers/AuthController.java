package com.blogapp.controllers;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.blogapp.payloads.JWTAuthRequest;
import com.blogapp.payloads.JWTAuthResponse;
import com.blogapp.security.JWTTokenHelper;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	@Autowired
	private JWTTokenHelper jwtTokenHelper;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/login")
	public ResponseEntity<JWTAuthResponse> createToken(@RequestBody JWTAuthRequest authRequest) throws Exception {
		this.authenticate(authRequest.getUsername(), authRequest.getPassword());
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(authRequest.getUsername());
		String generatedToken = this.jwtTokenHelper.generateToken(userDetails);
		JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
		jwtAuthResponse.setToken(generatedToken);
		return new ResponseEntity<JWTAuthResponse>(jwtAuthResponse, HttpStatus.OK);
	}

	private void authenticate(String userName, String password) throws Exception {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userName,
				password);
		try {
			this.authenticationManager.authenticate(authenticationToken);// TODO:Exception Handling Globally
		} catch (BadCredentialsException e) {
			System.err.println("Invalid Details ! !");
			throw new Exception("Invalid UserName or Password !");
		}
	}
}
