package com.siddhartha.garments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siddhartha.garments.request.OperatorSaveRequest;
import com.siddhartha.garments.response.CommonResponse;
import com.siddhartha.garments.service.OperatorMasterService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/operator")
@Api(tags = "MASTER : OPERATOR CONTROLLER",description = "API'S")
public class OperatorMasterController {
	
	@Autowired
	private OperatorMasterService service;
	
	
	@PostMapping("/save")
	public CommonResponse saveOperator(@RequestBody OperatorSaveRequest req) {
		return service.saveOperator(req);
	}
	
	@GetMapping("/getAll")
	public CommonResponse getAllOperator() {
		return service.getAllOperator();
	}
	
	@GetMapping("/edit/{operatorId}")
	public CommonResponse editOperator(@PathVariable("operatorId") String operatorId) {
		return service.editOperator(operatorId);
	}
	
	@GetMapping("/delete/{operatorId}")
	public CommonResponse deleteOperator(@PathVariable("operatorId") String operatorId) {
		return service.deleteOperator(operatorId);
	}
	
	
	
}
