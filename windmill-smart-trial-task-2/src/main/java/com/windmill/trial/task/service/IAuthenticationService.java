package com.windmill.trial.task.service;

import org.springframework.stereotype.Service;

import com.windmill.trial.task.model.LoginResponse;
import com.windmill.trial.task.model.UserDetails;

@Service
public interface IAuthenticationService {

	public void createUser(UserDetails userDetails, String provider);
	
	public LoginResponse loginUser(UserDetails userDetails, String provider);
	
	public UserDetails getUserDetails(String userId, String token);
}
