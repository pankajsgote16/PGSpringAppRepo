package com.blogapp.payloads;

import javax.persistence.Entity;

import lombok.Data;

@Data
public class JWTAuthRequest {

	private String username;
	
	private String password;
}
