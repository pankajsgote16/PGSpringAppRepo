package com.blogapp.services;

import java.util.List;

import com.blogapp.payloads.UserDto;


public interface UserService {
	
	UserDto generateUser(UserDto userDto);
	
	UserDto updateUser(UserDto userDto,Integer userId);
	
	UserDto fetchUserById(Integer userId);
	
	List<UserDto> fetchAllUsers();
	
	void deleteUser(Integer userId);
	
}
