package com.isil.eco.Controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.isil.eco.Exceptions.ClientValidationException;
import com.isil.eco.Models.User;
import com.isil.eco.Services.UserService;
import com.isil.eco.helpers.ModelValidator;
import com.isil.eco.security.jwt.AuthEntryPointJwt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isil.eco.payload.request.LoginRequest;
import com.isil.eco.payload.response.JwtResponse;
import com.isil.eco.payload.response.MessageResponse;
import com.isil.eco.security.jwt.JwtUtils;
import com.isil.eco.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {
	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	UserService userService;

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		// 	logger.debug(loginRequest.getPassword());
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		String role = userDetails.getRole();

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 role,
												 userDetails.getFname(),
												 userDetails.getLname()));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody @Valid User user, BindingResult bindingResult) {

		if(bindingResult.hasErrors()) {
			throw new ClientValidationException(ModelValidator.getErrorsFromBindingResult(bindingResult));
		}
		if (userService.existsByEmail(user.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		user.setPassword(encoder.encode(user.getPassword()));
		String strRoles = user.getRole();
		String username = user.getUsername();

		if (strRoles == null) {
			user.setRole("ROLE_CLIENT");
		}
		if (username == null) {
			user.setUsername(user.getLname()+(Math.floor(Math.random()*9999)));
		}

		User savedUser = userService.saveClient(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!",savedUser));
	}
}
