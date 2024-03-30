package com.siddhartha.garments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siddhartha.garments.dto.EditOrderDetailsReq;
import com.siddhartha.garments.dto.InsertSizeCalcRequest;
import com.siddhartha.garments.request.InserSizeColorRequest;
import com.siddhartha.garments.response.CommonResponse;
import com.siddhartha.garments.response.InsertProductCalcReq;
import com.siddhartha.garments.service.MetalCalculationService;

@RestController
@RequestMapping("/metal")
public class MetalCalculationController {
	
	
	@Autowired
	private MetalCalculationService service;
	
	
	@PostMapping("/generate/size/calc")
	public CommonResponse generateSizeCalc(@RequestBody EditOrderDetailsReq req) {
		return service.generateSizeCalc(req);
	}
	
	@PostMapping("/generate/size/color/calc")
	public CommonResponse generateSizeColorCalc(@RequestBody EditOrderDetailsReq req) {
		return service.generateSizeColorCalc(req);
	}
	
	@PostMapping("/generate/product/calc")
	public CommonResponse generateProductCalc(@RequestBody EditOrderDetailsReq req) {
		return service.generateProductCalc(req);
	}
	
	@PostMapping("/view/product/calc")
	public CommonResponse viewProductCalc(@RequestBody EditOrderDetailsReq req) {
		return service.viewProductCalc(req);
	}
	
	@PostMapping("/insert/product/calc")
	public CommonResponse insertProductCalc(@RequestBody InsertProductCalcReq req) {
		return service.insertProductCalc(req);
	}

	@PostMapping("/insert/size/calc")
	public CommonResponse doInsertSizeCalc(@RequestBody InsertSizeCalcRequest req) {
		return service.doInsertSizeCalc(req);
	}
	
	@PostMapping("/view/size/calc")
	public CommonResponse viewSizeCalc(@RequestBody EditOrderDetailsReq req) {
		return service.viewSizeCalc(req);
	}
	
	@PostMapping("/view/size/color/calc")
	public CommonResponse viewSizeColorCalc(@RequestBody EditOrderDetailsReq req) {
		return service.viewSizeColorCalc(req);
	}
	
	@PostMapping("/insert/size/color/calc")
	public CommonResponse insertSizeColorCalc(@RequestBody InserSizeColorRequest req) {
		return service.insertSizeColorCalc(req);
	}
	
}
