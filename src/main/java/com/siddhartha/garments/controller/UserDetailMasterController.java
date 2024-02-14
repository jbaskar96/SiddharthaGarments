package com.siddhartha.garments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siddhartha.garments.request.UserDetailsRequest;
import com.siddhartha.garments.response.CommonResponse;
import com.siddhartha.garments.service.UserDetailsMasterService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/userdetail")
@Api(tags = "MASTER : USER DETAILS CONTROLLER",description = "API'S")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserDetailMasterController {
	
	@Autowired
	private UserDetailsMasterService service;
	
	
	@PostMapping("/save")
	public CommonResponse saveUserDetails(@RequestBody UserDetailsRequest req) {
		return service.saveUserDetails(req);
	}
	
	@GetMapping("/getAll")
	public CommonResponse getAllUserDetails() {
		return service.getAllUserDetails();
	}

	@GetMapping("/edit/{userId}")
	public CommonResponse editUseDetails(@PathVariable("userId") String userId) {
		return service.editUseDetails(userId);
	}

	
}
