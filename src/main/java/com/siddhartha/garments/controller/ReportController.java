package com.siddhartha.garments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siddhartha.garments.request.WorkerEntryDetailsReq;
import com.siddhartha.garments.response.CommonResponse;
import com.siddhartha.garments.service.ReportService;


@RestController
@RequestMapping("/report")
public class ReportController {
	
	@Autowired
	private ReportService service;
	
	
	@PostMapping("/worker")
	public CommonResponse getWorkerReport(@RequestBody WorkerEntryDetailsReq req ) {
		return service.getWorkerReport(req);
	}

}
