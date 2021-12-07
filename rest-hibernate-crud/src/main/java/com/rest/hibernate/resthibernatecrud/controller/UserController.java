package com.rest.hibernate.resthibernatecrud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.hibernate.resthibernatecrud.exception.ResourceNotFoundException;
import com.rest.hibernate.resthibernatecrud.models.User;
import com.rest.hibernate.resthibernatecrud.repository.UserRepository;

@RestController
@RequestMapping("api/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	// Get all users
	@GetMapping
	public List<User> getAll() {
		return this.userRepository.findAll();
	}
	
	// Get all users by id
	@GetMapping("/{id}")
	public User getUserById(@PathVariable(value="id") long id) {
		return this.userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
	}
	
	// Create user
	@PostMapping
	public User createUser(@RequestBody User user) {
		return this.userRepository.save(user);
	}
	
	// Update user by id
	@PutMapping("/{id}")
	public User updateUser(@RequestBody User user, @PathVariable(value="id") long id) {
		User existingUser = this.userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
		
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());
		
		return this.userRepository.save(existingUser);
		
	}
	
	// Delete user by id
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable(value="id") long id) {
		User existingUser = this.userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
		
		this.userRepository.delete(existingUser);
		return ResponseEntity.ok().build();
	}
}
