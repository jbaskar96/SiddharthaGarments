package com.siddhartha.garments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siddhartha.garments.request.ColorSaveRequest;
import com.siddhartha.garments.response.CommonResponse;
import com.siddhartha.garments.service.ProductColorService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/color")
@Api(tags = "MASTER : COLOR CONTROLLER",description = "API'S")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductColorController {
	
	@Autowired
	private ProductColorService service;
	
	
	@PostMapping("/save")
	public CommonResponse saveColor(@RequestBody ColorSaveRequest req) {
		return service.saveColor(req);
	}
	
	@GetMapping("/getAll")
	public CommonResponse getAllColor(){
		return service.getAllColor();
	}
	
	@GetMapping("/edit/{colorId}")
	public CommonResponse editColor(@PathVariable("colorId") Integer colorId){
		return service.editColor(colorId);
	}
	
	@GetMapping("/delete/{colorId}")
	public CommonResponse deleteColor(@PathVariable("colorId") Integer colorId){
		return service.deleteColor(colorId);
	}

}
