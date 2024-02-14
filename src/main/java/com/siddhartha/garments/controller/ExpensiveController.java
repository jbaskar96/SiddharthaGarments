package com.siddhartha.garments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siddhartha.garments.request.ExpensiveRequest;
import com.siddhartha.garments.response.CommonResponse;
import com.siddhartha.garments.service.ExpensiveService;

@RestController
@RequestMapping("/expensive")
public class ExpensiveController {
	
	@Autowired
	private ExpensiveService service;
	
	
	@PostMapping("/save")
	public CommonResponse saveExpensive(@RequestBody ExpensiveRequest req) {
		return service.saveExpensive(req);
	}

	@GetMapping("/getAll")
	public CommonResponse getAllExpensive() {
		return service.getAllExpensive();
	}
	
	@GetMapping("/edit/{SerialNo}")
	public CommonResponse editExpensive(@PathVariable("SerialNo") Integer serialNo) {
		return service.editExpensive(serialNo);
	}
	
}
