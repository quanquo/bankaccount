package com.dangvis.account.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dangvis.account.model.User;
import com.dangvis.account.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public Optional<User> getUserByUserName(String username) {
		return userRepository.findById(username);
	}

	public Optional<User> getUserByName(String name) {
		return userRepository.findByName(name);
	}

	public User createUser(User user) {
		if (userRepository.existsByUsername(user.getUsername())) {
			throw new RuntimeException("Username already exists: " + user.getUsername());
		}
		return userRepository.save(user);
	}

	public User updateUser(String username, User userDetails) {
		User user = userRepository.findById(username)
				.orElseThrow(() -> new RuntimeException("User not found with id: " + username));

		user.setName(userDetails.getName());
		user.setUsername(userDetails.getUsername());

		return userRepository.save(user);
	}
}
