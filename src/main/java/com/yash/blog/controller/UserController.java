package com.yash.blog.controller;

import java.util.List;

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

import com.yash.blog.payloads.ApiResponse;
import com.yash.blog.payloads.UserDTO;
import com.yash.blog.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	// get all user details
	@GetMapping("/")
	public ResponseEntity<List<UserDTO>> getAllUsers(){
		List<UserDTO> allUsers = this.userService.getAllUsers();
		return new ResponseEntity<>(allUsers, HttpStatus.OK);
	}
	
	// get single user by id
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable String id){
		UserDTO userDTO = this.userService.getUserById(Integer.parseInt(id));
		return new ResponseEntity<>(userDTO, HttpStatus.FOUND);
	}
	
	// create new user
	@PostMapping("/")
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
		UserDTO newUser = this.userService.createUser(userDTO);
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}
	
	// update existing user details
	@PutMapping("/{id}")
	public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO, @PathVariable String id){
		UserDTO updateUser = this.userService.updateUser(userDTO, Integer.parseInt(id));
		return new ResponseEntity<>(updateUser, HttpStatus.OK);
	}
	
	// delete existing user
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable String id){
		this.userService.deleteUser(Integer.parseInt(id));
		return new ResponseEntity<>(new ApiResponse("User deleted Successfully", "Done"), HttpStatus.OK);
	}
}
