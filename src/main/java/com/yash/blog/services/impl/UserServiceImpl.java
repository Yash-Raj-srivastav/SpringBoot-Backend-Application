package com.yash.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.blog.entities.User;
import com.yash.blog.payloads.UserDTO;
import com.yash.blog.repositories.UserRepo;
import com.yash.blog.services.UserService;
import com.yash.blog.exceptions.*;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	@Override
	public UserDTO createUser(UserDTO userDTO) {
		User user = this.dtoToUser(userDTO);
		User savedUser = this.userRepo.save(user);
		return this.userToDTO(savedUser);
	}

	@Override
	public UserDTO updateUser(UserDTO userDTO, Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		user.setName(userDTO.getName());
		user.setEmail(userDTO.getEmail());
		user.setPassword(userDTO.getPassword());
		user.setAbout(userDTO.getAbout());
		User updatedUser = this.userRepo.save(user);
		UserDTO userDTOupdated = this.userToDTO(updatedUser);
		return userDTOupdated;
	}

	@Override
	public UserDTO getUserById(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		return this.userToDTO(user);
	}

	@Override
	public List<UserDTO> getAllUsers() {
		List<User> users  = this.userRepo.findAll();
		List<UserDTO> userDTO = users.stream().map(user -> this.userToDTO(user)).collect(Collectors.toList());
		return userDTO;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		this.userRepo.delete(user);
	}
	
	public User dtoToUser(UserDTO userDTO) {
		User user = new User();
		user.setId(userDTO.getId());
		user.setName(userDTO.getName());
		user.setEmail(userDTO.getEmail());
		user.setAbout(userDTO.getAbout());
		user.setPassword(userDTO.getPassword());
		return user;
	}
	
	public UserDTO userToDTO(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setName(user.getName());
		userDTO.setEmail(user.getEmail());
		userDTO.setAbout(user.getAbout());
		userDTO.setPassword(user.getPassword());
		return userDTO;
	}

}
