package com.siddhartha.garments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siddhartha.garments.request.PurchaseReportReq;
import com.siddhartha.garments.request.WorkerEntryDetailsReq;
import com.siddhartha.garments.response.CommonResponse;
import com.siddhartha.garments.service.ReportService;

import io.swagger.annotations.Api;


@RestController
@RequestMapping("/report")
@Api(tags = "OPERATOR WORK REPORT CONTROLLER",description = "API'S")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ReportController {
	
	@Autowired
	private ReportService service;
	
	
	@PostMapping("/worker")
	public CommonResponse getWorkerReport(@RequestBody WorkerEntryDetailsReq req ) {
		return service.getWorkerReport(req);
	}

	@PostMapping("/export/excel")
	public CommonResponse exportExcel(@RequestBody WorkerEntryDetailsReq req) {
		return service.exportExcel(req);
	}
	
	@PostMapping("/purchase")
	public CommonResponse getPurchaseReport(@RequestBody PurchaseReportReq req) {
		return service.getPurchaseReport(req);
	}
	
}
