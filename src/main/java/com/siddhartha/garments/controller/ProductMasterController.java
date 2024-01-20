package com.siddhartha.garments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siddhartha.garments.service.ProductMasterService;

@RestController
@RequestMapping("/product")
public class ProductMasterController {
	
	@Autowired
	private ProductMasterService service;

}
