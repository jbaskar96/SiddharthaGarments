package com.siddhartha.garments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siddhartha.garments.request.ColorDetailsRequest;
import com.siddhartha.garments.request.DeliveryMoveRequest;
import com.siddhartha.garments.request.EditColorDetailReq;
import com.siddhartha.garments.request.EditMeterialDetailReq;
import com.siddhartha.garments.request.GetColorDetailsReq;
import com.siddhartha.garments.request.LotDetailsRequest;
import com.siddhartha.garments.request.MeterialDetailsReq;
import com.siddhartha.garments.request.MeterialDetailsRequest;
import com.siddhartha.garments.request.ProductionMoveReq;
import com.siddhartha.garments.response.CommonResponse;
import com.siddhartha.garments.service.LotCreationService;

@RestController
@RequestMapping("/order")
public class LotCreationController {
	
	
	@Autowired
	private LotCreationService service;
	
	
	@PostMapping("/creation")
	public CommonResponse lotCreation(@RequestBody LotDetailsRequest req) {
		return service.lotCreation(req);
	}
	
	@GetMapping("/getOrderDeatils/{orderId}")
	public CommonResponse getLotDetails(@PathVariable("orderId") String orderId) {
		return service.getLotDetails(orderId);
	}

	@PostMapping("/challan/color/save")
	public CommonResponse challanColorCreation(@RequestBody ColorDetailsRequest req) {
		return service.challanColorCreation(req);
	}
	
	@PostMapping("/challan/meterial/save")
	public CommonResponse challanMeterialSave(@RequestBody MeterialDetailsRequest req) {
		return service.challanMeterialSave(req);
	}
	
	@PostMapping("/get/color/detail")
	public CommonResponse getColorDetails(@RequestBody GetColorDetailsReq req) {
		return service.getColorDetails(req);
	}
	
	@PostMapping("/get/meterial/detail")
	public CommonResponse getMeterialDetails(@RequestBody MeterialDetailsReq req) {
		return service.getMeterialDetails(req);
	}
	
	@PostMapping("/edit/color/detail")
	public CommonResponse editColorDetail(@RequestBody EditColorDetailReq req) {
		return service.editColorDetail(req);
	}
	
	@PostMapping("/edit/meterial/detail")
	public CommonResponse editMeterialDetail(@RequestBody EditMeterialDetailReq req ) {
		return service.editMeterialDetail(req);
	}
	
	@GetMapping("/getAllOrderDeatils")
	public CommonResponse getOrderDeatils() {
		return service.getOrderDeatils("Y");
	}
	
	@GetMapping("/getProductionDeatils")
	public CommonResponse getProductionDeatils() {
		return service.getOrderDeatils("P");
	}
	
	@GetMapping("/getDeliveryDeatils")
	public CommonResponse getDeliveryDeatils() {
		return service.getOrderDeatils("D");
	}
	
	@PostMapping("/move/production")
	public CommonResponse moveProduction(@RequestBody ProductionMoveReq req) {
		return service.moveProduction(req);
	}
	
	@PostMapping("/move/delivery")
	public CommonResponse moveDelivery(@RequestBody DeliveryMoveRequest req) {
		return service.moveDelivery(req);
	}
	
}
