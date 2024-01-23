package com.siddhartha.garments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siddhartha.garments.request.StateSaveRequest;
import com.siddhartha.garments.response.CommonResponse;
import com.siddhartha.garments.service.StateMasterService;

@RestController
@RequestMapping("/state")
public class StateMasterController {
	
	@Autowired
	private StateMasterService service;
	
	
	@PostMapping("/save")
	public CommonResponse saveState(@RequestBody StateSaveRequest req) {
		return service.saveState(req);
	}

	@GetMapping("/getAll")
	public CommonResponse getAllState() {
		return service.getAllState();
	}
	
	@GetMapping("/edit/{stateCode}")
	public CommonResponse editState(@PathVariable("stateCode") Integer stateCode) {
		return service.editState(stateCode);
	}
	
	@GetMapping("/delete/{stateCode}")
	public CommonResponse deleteState(@PathVariable("stateCode") Integer stateCode) {
		return service.deleteState(stateCode);
	}
	

}
