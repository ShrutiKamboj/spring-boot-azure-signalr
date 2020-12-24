package com.windmill.trial.task.model;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public @Data class UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String userId;
	
	private String userName;
	
	private String password;
}
