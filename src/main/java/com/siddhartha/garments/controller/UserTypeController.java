package com.siddhartha.garments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siddhartha.garments.service.UserTypeService;

@RestController
@RequestMapping("/usertype")
public class UserTypeController {
	
	@Autowired
	private UserTypeService service;

}
