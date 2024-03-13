package com.siddhartha.garments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siddhartha.garments.request.PurchaseReportReq;
import com.siddhartha.garments.request.WorkerEntryDetailsReq;
import com.siddhartha.garments.response.CommonResponse;
import com.siddhartha.garments.response.ExpensiveReportRequest;
import com.siddhartha.garments.response.OrderReportReq;
import com.siddhartha.garments.response.WorkerReportReq;
import com.siddhartha.garments.service.ReportService;

import io.swagger.annotations.Api;


@RestController
@RequestMapping("/report")
@Api(tags = "OPERATOR WORK REPORT CONTROLLER",description = "API'S")
public class ReportController {
	
	@Autowired
	private ReportService service;
	
	
	@PostMapping("/worker")
	public CommonResponse getWorkerReport(@RequestBody WorkerReportReq req ) {
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
	
	@PostMapping("/order")
	public CommonResponse getOrderReport(@RequestBody OrderReportReq req) {
		return service.getOrderReport(req);
	}
	
	@PostMapping("/expensive")
	public CommonResponse getExpensiveReport(@RequestBody ExpensiveReportRequest req) {
		return service.getExpensiveReport(req);
	}
	
	
}
