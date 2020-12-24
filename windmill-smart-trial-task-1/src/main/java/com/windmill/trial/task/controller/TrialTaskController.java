package com.windmill.trial.task.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TrialTaskController {

	@RequestMapping(value = "/search/longeststring", method = RequestMethod.POST)
	public ResponseEntity<String> getLongesString(
			@RequestBody List<String> input) {
		
		String response;
		if (input.size() <= 0) {
			response = "Empty String";
		} else if (input.size() == 1) {
			response = input.get(0);
		} else {
			List<String> output = input.stream().sorted((String o1, String o2) -> {
				// TODO Auto-generated method stub
				return o2.length()-o1.length();
			}
					).collect(Collectors.toList());

			response = output.get(0);
		}
		
		return new ResponseEntity<String>(response, HttpStatus.OK);
		
	}
	

	@RequestMapping(value="/filter/list", method = RequestMethod.DELETE)
	public ResponseEntity<List<String>> filterList(
			@RequestBody List<String> input) {
		
		List<String> output = input.stream().filter(a->a.length() <= 10).collect(Collectors.toList());
		
		return new ResponseEntity<List<String>>(output, HttpStatus.OK);
	}
}
