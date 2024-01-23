package com.siddhartha.garments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siddhartha.garments.request.UserDetailsRequest;
import com.siddhartha.garments.response.CommonResponse;
import com.siddhartha.garments.service.UserDetailsMasterService;

@RestController
@RequestMapping("/userdetail")
public class UserDetailMasterController {
	
	@Autowired
	private UserDetailsMasterService service;
	
	
	
	@PostMapping("/save")
	public CommonResponse saveUserDetails(@RequestBody UserDetailsRequest req) {
		return service.saveUserDetails(req);
	}

}
