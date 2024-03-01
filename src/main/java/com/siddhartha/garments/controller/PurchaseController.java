package com.siddhartha.garments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siddhartha.garments.request.PurchaseRequest;
import com.siddhartha.garments.response.CommonResponse;
import com.siddhartha.garments.service.PurchaseService;


@RestController
@RequestMapping("/purchase")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PurchaseController {
	
	
	@Autowired
	private PurchaseService service;
	
	
	@PostMapping("/save")
	private CommonResponse savePurchase(@RequestBody PurchaseRequest req) {
		return service.savePurchase(req);
	}
	
	@GetMapping("/getAll")
	private CommonResponse getAll() {
		return service.getAll();
	}
	
	@GetMapping("/edit/{serialNo}")
	private CommonResponse getAll(@PathVariable("serialNo") Long serialNo) {
		return service.getAll(serialNo);
	}

}
