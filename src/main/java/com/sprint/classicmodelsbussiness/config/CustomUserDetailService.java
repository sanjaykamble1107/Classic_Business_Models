package com.sprint.classicmodelsbussiness.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sprint.classicmodelsbussiness.entity.User;
import com.sprint.classicmodelsbussiness.repository.UserRepository;




@Service
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		
		   Optional<User> user = userRepository.findByName(username);
	        if (user.isEmpty())
	            throw new UsernameNotFoundException("Username not found",null);
	        
	        return new org.springframework.security.core.userdetails.User(user.get().getName(), user.get().getPassword(),user.get().getAuthorities());
	    }
		
	}


