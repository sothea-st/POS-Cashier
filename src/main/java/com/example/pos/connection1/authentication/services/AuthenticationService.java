package com.example.pos.connection1.authentication.services;

import com.example.pos.connection1.authentication.dtos.LoginUserDto;
import com.example.pos.connection1.authentication.dtos.RegisterUserDto;
import com.example.pos.connection1.repository.UserRepository;
import com.example.pos.connection1.entity.User;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthenticationService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;

	public AuthenticationService(
			UserRepository userRepository,
			AuthenticationManager authenticationManager,
			PasswordEncoder passwordEncoder) {
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public User signup(RegisterUserDto input) {

		int userCount = userRepository.userCount();
	 
		String userIdStr = "";
		userCount++;
	 
		if( userCount < 10 ) {
			userIdStr="000"+userCount;
		} else if ( userCount < 100 ) {
			userIdStr="00"+userCount;
		} else if ( userCount < 1000 ) {
			userIdStr="0"+userCount;
		} else {
			userIdStr="0"+userCount;
		}

		var user = new User()
				.setFullName(input.getFullName())
				.setUserCode(userIdStr)
				.setPassword(passwordEncoder.encode(input.getPassword()));
				user.setRole(input.getRole());
		return userRepository.save(user);
	}

	public User authenticate(LoginUserDto input) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						input.getUserCode(),
						input.getPassword()));

		return userRepository.findByUserCode(input.getUserCode()).orElseThrow();

	}

	public List<User> allUsers() {
		List<User> users = new ArrayList<>();

		userRepository.findAll().forEach(users::add);

		return users;
	}
}