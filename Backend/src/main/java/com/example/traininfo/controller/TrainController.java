package com.example.traininfo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;


import com.example.traininfo.service.TrainService;

@RestController
@CrossOrigin
public class TrainController {
	
	@Autowired
	private final TrainService trainService;

	@Autowired
	public TrainController(TrainService trainService) {
		this.trainService = trainService;
	}

}
