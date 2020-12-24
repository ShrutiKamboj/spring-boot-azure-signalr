package citi.hackathon.predictor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import citi.hackathon.predictor.model.MappedLocation;
import citi.hackathon.predictor.service.MarketPredictorService;

@RestController
public class MarketPredictorController {

	@Autowired
	MarketPredictorService service;
	
	@RequestMapping(value = "/locations", method = RequestMethod.GET)
	public ResponseEntity<List<MappedLocation>> getLocations() {
		return new ResponseEntity<List<MappedLocation>>(service.getLocations(), HttpStatus.OK);
	}
}
