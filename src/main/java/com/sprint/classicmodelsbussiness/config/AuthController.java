package com.sprint.classicmodelsbussiness.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.classicmodelsbussiness.entity.User;
import com.sprint.classicmodelsbussiness.repository.UserRepository;




@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200/")
public class AuthController {

	@Autowired
	private AuthenticateService authenticateService;
	@Autowired
	private CustomUserDetailService userDetailService;
	


	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtService jwtService;
	@Autowired
	private UserRepository userRepository;
	
	

//	private Logger logger = LoggerFactory.getLogger(AuthController.class);

	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

		this.doAuthenticate(request.getname(),request.getPassword());

		UserDetails userDetails = userDetailService.loadUserByUsername(request.getname());
		String token = this.jwtService.generateToken(userDetails);

		JwtResponse response = new JwtResponse();
		response.setJwtToken(token);
		response.setUsername(userDetails.getUsername());
		response.setrole(userRepository.findByName(userDetails.getUsername()).get().getRoles());

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
		
		if (userRepository.findByName(registerRequest.getName()).isPresent())
            return new ResponseEntity<>("User already exists", HttpStatus.NOT_ACCEPTABLE);

		return ResponseEntity.ok(authenticateService.register(registerRequest));

	}

	
	

	private void doAuthenticate(String userName, String password) {

		Optional<User> optional = userRepository.findByName(userName);
		
		if(optional.isEmpty()) 
		{
			//throw new UsernameNotFoundException("User not found with that name");
			System.out.println("*****####");
		}
		System.out.println(userName+"---"+password);
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userName, password,optional.get().getAuthorities());

		try {
			authenticationManager.authenticate(authentication);

		} catch (BadCredentialsException ex) {
			throw new BadCredentialsException("Invalid Username or password !!");
		}
	}

	
}
