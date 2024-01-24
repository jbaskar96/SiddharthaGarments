package com.siddhartha.garments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siddhartha.garments.response.CommonResponse;
import com.siddhartha.garments.service.DropDownService;

@RestController
@RequestMapping("/dropdown")
public class DropDownController {
	
	
	@Autowired
	private DropDownService service;
	
	
	@GetMapping("/section")
	public CommonResponse section() {
		return service.section();
	}
	
	@GetMapping("/product")
	public CommonResponse product() {
		return service.product();
	}
	
	@GetMapping("/usertype")
	public CommonResponse usertype() {
		return service.usertype();
	}
	
	@GetMapping("/color")
	public CommonResponse color() {
		return service.color();
	}
	
	@GetMapping("/state")
	public CommonResponse state() {
		return service.state();
	}
	
	@GetMapping("/district/{stateCode}")
	public CommonResponse district(@PathVariable("stateCode") String stateCode) {
		return service.district(stateCode);
	}

	@GetMapping("/operator")
	public CommonResponse operator() {
		return service.operator();
	}
	
	@GetMapping("/size")
	public CommonResponse size() {
		return service.size();
	}
	
}
