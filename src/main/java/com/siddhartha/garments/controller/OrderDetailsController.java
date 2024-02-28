package com.siddhartha.garments.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siddhartha.garments.dto.EditOrderDetailsReq;
import com.siddhartha.garments.dto.GetOrderSizeColorReq;
import com.siddhartha.garments.dto.OrderChallanColorReq;
import com.siddhartha.garments.dto.OrderDetailsRequest;
import com.siddhartha.garments.response.CommonResponse;
import com.siddhartha.garments.service.OrderDetailsService;

@RestController
@RequestMapping("/order")
public class OrderDetailsController {
	
	@Autowired
	private OrderDetailsService service;
	
	
	@PostMapping("/save")
	public CommonResponse saveOrder(@RequestBody OrderDetailsRequest req) {
		return service.saveOrder(req);
	}
	
	@PostMapping("/edit")
	public CommonResponse editOrder(@RequestBody EditOrderDetailsReq req) {
		return service.editOrder(req);
	}
	
	@PostMapping("/save/color")
	public CommonResponse saveColor(@RequestBody List<OrderChallanColorReq>	 req) {
		return service.saveColor(req);
	}
	
	@PostMapping("/getOrderColorDetails")
	public CommonResponse getOrderColorDetails(@RequestBody GetOrderSizeColorReq req) {
		return service.getOrderColorDetails(req);
	}
	
	@PostMapping("/editOrderColorDetails")
	public CommonResponse editOrderColorDetails(@RequestBody GetOrderSizeColorReq req) {
		return service.editOrderColorDetails(req);
	}
	
	@GetMapping("/getAllOrderDeatils")
	public CommonResponse getAllOrderDeatils() {
		return service.getAllOrderDeatils("Y");
	}

}
