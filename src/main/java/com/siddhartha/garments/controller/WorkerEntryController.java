package com.siddhartha.garments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siddhartha.garments.dto.EditOrderDetailsReq;
import com.siddhartha.garments.request.WorkerEntryDetailsReq;
import com.siddhartha.garments.response.CommonResponse;
import com.siddhartha.garments.service.WorkerEntryService;

import io.swagger.annotations.Api;

@RestController	
@RequestMapping("/worker")
@Api(tags = "WORKER DATA ENTRY CONTROLLER",description = "API'S")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class WorkerEntryController {
	
	@Autowired
	private WorkerEntryService service;
	
	@PostMapping("/entry/save")
	public CommonResponse workerEntrySave(@RequestBody WorkerEntryDetailsReq req) {
		return service.workerEntrySave(req);
	}
	
	@PostMapping("/getWorkerEntryByOrderId")
	public CommonResponse getWorkerEntryByOrderId(@RequestBody EditOrderDetailsReq req) {
		return service.getWorkerEntryByOrderId(req.getOrderId());
	}
	
	@GetMapping("/getAllWorkerEntryDetail")
	public CommonResponse getAllWorkerEntryDetail() {
		return service.getAllWorkerEntryDetail();
	}
	
	@GetMapping("/editWorkerEntryDetail/{serialNo}")
	public CommonResponse editWorkerEntryDetail(@PathVariable("serialNo") String serialNo) {
		return service.editWorkerEntryDetail(serialNo);
	}
	

}
