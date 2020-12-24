package sk.user.auth.controller;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sk.user.auth.service.AuthenticationService;

@RestController
public class AuthenticationController {
	
	@Autowired
	AuthenticationService authService;
	
	@Autowired
	RedisTemplate redisTemplate;

	@RequestMapping(value="/users", method = RequestMethod.POST)
	public ResponseEntity<String> sendOTP(@RequestParam String toNumber,
			@RequestParam String fromNumber) {
		String response = authService.sendOTP(toNumber, fromNumber, "Hi");
		
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
}
