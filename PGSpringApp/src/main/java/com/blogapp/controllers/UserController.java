package com.blogapp.controllers;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.blogapp.payloads.ApiResponse;
import com.blogapp.payloads.UserDto;
import com.blogapp.services.UserService;
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<UserDto> generateUser(@Valid @RequestBody UserDto userDto) {
		UserDto generatedUser = userService.generateUser(userDto);
		return new ResponseEntity<>(generatedUser, HttpStatus.CREATED);
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable Integer userId) {
		UserDto updatedUser = userService.updateUser(userDto, userId);
		return new ResponseEntity<UserDto>(updatedUser, HttpStatus.OK);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId) {
		UserDto userById = userService.fetchUserById(userId);
		return new ResponseEntity<UserDto>(userById, HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> fetchAllUsers() {
		List<UserDto> allUsers = userService.fetchAllUsers();
		return new ResponseEntity<List<UserDto>>(allUsers,HttpStatus.OK);
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId) {
		userService.deleteUser(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted succesfull having Id :"+" "+userId, true),HttpStatus.OK);
	}
}
