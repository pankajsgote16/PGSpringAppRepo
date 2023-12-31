package com.blogapp.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapp.entities.User;
import com.blogapp.exceptions.ResourceNotFoundException;
import com.blogapp.payloads.UserDto;
import com.blogapp.repositories.UserRepo;
import com.blogapp.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto generateUser(UserDto userDto) {
		User user = mapUserDtotoUser(userDto);
		User savedUser = userRepo.save(user);
		return mapUsertoUserDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		user.setName(userDto.getName());
		user.setEmailId(userDto.getEmailId());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		User updatedUser = userRepo.save(user);
		UserDto mappedUser = mapUsertoUserDto(updatedUser);
		return mappedUser;
	}

	@Override
	public UserDto fetchUserById(Integer userId) {
		User userById = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		UserDto mapUsertoUserDto = mapUsertoUserDto(userById);
		return mapUsertoUserDto;
	}

	@Override
	public List<UserDto> fetchAllUsers() {
		List<User> allUsers = userRepo.findAll();
		List<UserDto> allUsersDto = allUsers.stream().map(user -> mapUsertoUserDto(user)).collect(Collectors.toList());
		return allUsersDto;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		userRepo.delete(user);
	}

	/*
	 * This method will convert userdto to user object
	 */
	public User mapUserDtotoUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmailId(userDto.getEmailId());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		return user;

	}

	/*
	 * This method will convert user to userdto object
	 */
	public UserDto mapUsertoUserDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmailId(user.getEmailId());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
		return userDto;

	}
}
