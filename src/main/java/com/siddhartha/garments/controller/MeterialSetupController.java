package com.siddhartha.garments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siddhartha.garments.request.MeterialSetupRequest;
import com.siddhartha.garments.response.CommonResponse;
import com.siddhartha.garments.service.MeterialSetupService;

@RestController
@RequestMapping("/meterial/setup")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MeterialSetupController {
	
	
	@Autowired
	private MeterialSetupService service;
	
	
	@PostMapping("/save")
	public CommonResponse saveMeterialSetup(@RequestBody MeterialSetupRequest req) {
		return service.saveMeterialSetup(req);
	}

}
