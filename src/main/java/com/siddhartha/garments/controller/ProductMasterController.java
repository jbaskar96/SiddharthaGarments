package com.siddhartha.garments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siddhartha.garments.request.ProductSaveRequest;
import com.siddhartha.garments.response.CommonResponse;
import com.siddhartha.garments.service.ProductMasterService;

@RestController
@RequestMapping("/product")
public class ProductMasterController {
	
	@Autowired
	private ProductMasterService service;
	
	
	@PostMapping("/save")
	public CommonResponse saveProduct(@RequestBody ProductSaveRequest req) {
		return service.saveProduct(req);
	}
	
	@GetMapping("/getAll")
	public CommonResponse getAllProduct() {
		return service.getAllProduct();
	}
	
	@GetMapping("/edit/{productId}")
	public CommonResponse editProduct(@PathVariable("productId") Integer productId) {
		return service.editProduct(productId);
	}
	
	@GetMapping("/delete/{productId}")
	public CommonResponse deleteProduct(@PathVariable("productId") Integer productId) {
		return service.deleteProduct(productId);
	}

}
