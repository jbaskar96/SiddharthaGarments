package com.siddhartha.garments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siddhartha.garments.request.WorkerEntryDetailsReq;
import com.siddhartha.garments.response.CommonResponse;
import com.siddhartha.garments.service.WorkerEntryService;

import io.swagger.annotations.Api;

@RestController	
@RequestMapping("/worker")
@Api(tags = "WORKER DATA ENTRY CONTROLLER",description = "API'S")
public class WorkerEntryController {
	
	@Autowired
	private WorkerEntryService service;
	
	@PostMapping("/entry/save")
	public CommonResponse workerEntrySave(@RequestBody WorkerEntryDetailsReq req) {
		return service.workerEntrySave(req);
	}
	
	@GetMapping("/getWorkerEntryByOrderId/{orderId}")
	public CommonResponse getWorkerEntryByOrderId(@PathVariable("OrderId") String orderId) {
		return service.getWorkerEntryByOrderId(orderId);
	}
	
	@GetMapping("/getAllWorkerEntryDetail")
	public CommonResponse getAllWorkerEntryDetail() {
		return service.getAllWorkerEntryDetail();
	}
	

}
