package com.blogapp.exceptions;

public class ResourceNotFoundException extends RuntimeException{

	String resourceName;
	String fieldName;
	int fieldValue;
	String emailId;
	
	public ResourceNotFoundException(String resourceName, String fieldName, int fieldValue) {
		super(String.format("%s Not found with %s : %s",resourceName,fieldName,fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

	public ResourceNotFoundException(String resourceName, String fieldName, String emailId) {
		super(String.format("%s Not found with %s : %s",resourceName,fieldName,emailId));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.emailId = emailId;
	}
	
	
	
	

}
