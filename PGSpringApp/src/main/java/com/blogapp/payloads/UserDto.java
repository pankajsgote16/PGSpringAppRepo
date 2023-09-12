package com.blogapp.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private int Id;
	
	@NotEmpty
	@Size(min = 4,message = "Username must be min of 4 characters ! !")
	private String name;
	
	@Email(message = "Email Id is not valid ! !")
	private String emailId;
	
	@NotEmpty
	@Size(min = 3,max = 10,message = "Password must be min 3 chars and max 10 chars ! ! ")
	private String password;
	
	@NotEmpty
	private String about;

}
