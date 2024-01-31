package com.siddhartha.garments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siddhartha.garments.request.SizeMasterRequest;
import com.siddhartha.garments.response.CommonResponse;
import com.siddhartha.garments.service.ProductSizeService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/size")
@Api(tags = "MASTER : SIZE CONTROLLER",description = "API'S")
public class ProductSizeController {
	
	
	@Autowired
	private ProductSizeService service;
	
	
	
	@PostMapping("/save")
	public CommonResponse saveSize(@RequestBody SizeMasterRequest req) {
		return service.saveSize(req);
	}

	@GetMapping("/getAll")
	public CommonResponse getAllSize() {
		return service.getAllSize();
	}
	
	@GetMapping("/edit/{sizeId}")
	public CommonResponse editSize(@PathVariable("sizeId") Integer sizeId) {
		return service.editSize(sizeId);
	}
	
	@GetMapping("/delete/{sizeId}")
	public CommonResponse deleteSize(@PathVariable("sizeId") Integer sizeId) {
		return service.deleteSize(sizeId);
	}
	
	
}
