package com.siddhartha.garments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siddhartha.garments.request.UserTypeRequest;
import com.siddhartha.garments.response.CommonResponse;
import com.siddhartha.garments.service.UserTypeService;

@RestController
@RequestMapping("/usertype")
public class UserTypeController {
	
	@Autowired
	private UserTypeService service;
	
	
	@PostMapping("/save")
	public CommonResponse saveUserType(@RequestBody UserTypeRequest req) {
		return service.saveUserType(req);
	}
	
	@GetMapping("/getAll")
	public CommonResponse getAllUserType() {
		return service.getAllUserType();
	}
	
	@GetMapping("/edit/{userTypeId}")
	public CommonResponse editUserType(@PathVariable("userTypeId") Integer userTypeId) {
		return service.editUserType(userTypeId);
	}
	
	@GetMapping("/delete/{userTypeId}")
	public CommonResponse deleteUserType(@PathVariable("userTypeId") Integer userTypeId) {
		return service.deleteUserType(userTypeId);
	}

}
