package com.windmill.trial.task.utility;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {
	
	public String encodePassword(String pwd) {
		String encodedPwd = new BCryptPasswordEncoder().encode(pwd);
		return encodedPwd;
	}

}
