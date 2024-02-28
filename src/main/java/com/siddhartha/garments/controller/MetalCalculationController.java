package com.siddhartha.garments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siddhartha.garments.dto.EditOrderDetailsReq;
import com.siddhartha.garments.dto.InsertSizeCalcRequest;
import com.siddhartha.garments.response.CommonResponse;
import com.siddhartha.garments.service.MetalCalculationService;

@RestController
@RequestMapping("/metal")
public class MetalCalculationController {
	
	
	@Autowired
	private MetalCalculationService service;
	
	
	@PostMapping("generate/size/calc")
	public CommonResponse doSizeCalc(@RequestBody EditOrderDetailsReq req) {
		return service.doSizeCalc(req);
	}
	
	@PostMapping("generate/size/color/calc")
	public CommonResponse doSizeColorCalc(@RequestBody EditOrderDetailsReq req) {
		return service.doSizeColorCalc(req);
	}
	
	@PostMapping("insert/size/calc")
	public CommonResponse doInsertSizeCalc(@RequestBody InsertSizeCalcRequest req) {
		return service.doInsertSizeCalc(req);
	}
	
	@PostMapping("/view/size/calc")
	public CommonResponse viewSizeCalc(@RequestBody EditOrderDetailsReq req) {
		return service.viewSizeCalc(req);
	}
	
}
