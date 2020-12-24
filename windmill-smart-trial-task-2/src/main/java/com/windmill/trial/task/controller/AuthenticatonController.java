package com.windmill.trial.task.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.windmill.trial.task.model.LoginResponse;
import com.windmill.trial.task.model.UserDetails;
import com.windmill.trial.task.service.IAuthenticationService;

@RestController
public class AuthenticatonController {
	
	@Autowired
	IAuthenticationService authService;

	@RequestMapping(value="/signUp", method = RequestMethod.POST)
	public void createUser(
			@RequestBody UserDetails user,
			@RequestParam String provider) {
		
		authService.createUser(user, provider);
		
	}
	
	@RequestMapping(value="signIn", method = RequestMethod.POST)
	public ResponseEntity<LoginResponse> loginUser(
			@RequestBody UserDetails user,
			@RequestParam String provider) {
		LoginResponse loginResponse = null;
		
		loginResponse = authService.loginUser(user, provider);
		
		return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.OK);
	}
	
	@RequestMapping(value="/users/{userId}", method = RequestMethod.GET)
	public ResponseEntity<UserDetails> getUser(
			@PathVariable String userId,
			@RequestHeader String token) {
		
		UserDetails userDetails = null;
		
		String auth = token.substring(7);
		
		if (token == null || !token.startsWith("Bearer") || auth == null) {
			return new ResponseEntity<UserDetails>(userDetails, HttpStatus.UNAUTHORIZED);
		}
		
		userDetails = authService.getUserDetails(userId, auth);
		return new ResponseEntity<UserDetails>(userDetails, HttpStatus.OK);
	}
}
