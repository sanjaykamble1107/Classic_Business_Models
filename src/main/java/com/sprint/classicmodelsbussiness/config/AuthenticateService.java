package com.sprint.classicmodelsbussiness.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sprint.classicmodelsbussiness.entity.User;
import com.sprint.classicmodelsbussiness.repository.UserRepository;

@Service
public class AuthenticateService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtService jwtService;



	public JwtResponse register(RegisterRequest registerRequest) {

		User user = new User();

		user.setName(registerRequest.getName());

		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

		user.setRoles("ADMIN");

		userRepository.save(user);

		return new JwtResponse("user added successfully");
	}

}
