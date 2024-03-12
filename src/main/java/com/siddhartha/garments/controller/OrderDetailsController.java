package com.siddhartha.garments.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.siddhartha.garments.response.BoxCalculateReq;
import com.siddhartha.garments.response.CommonResponse;
import com.siddhartha.garments.response.OrderBillingRequest;
import com.siddhartha.garments.response.UpdateOrderStatusReq;
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
	
	@GetMapping("/getProductionOrders")
	public CommonResponse getProductionOrders() {
		return service.getAllOrderDeatils("P");
	}

	@GetMapping("/getDeliveryOrders")
	public CommonResponse getDeliveryOrders() {
		return service.getAllOrderDeatils("D");
	}
	
	@GetMapping("/getBillingOrders")
	public CommonResponse getBillingOrders() {
		return service.getAllOrderDeatils("B");
	}
	
	@PostMapping("/updateOrderStatus")
	public CommonResponse updateOrderStatus(@RequestBody UpdateOrderStatusReq req) {
		return service.updateOrderStatus(req);
	}
	
	@PostMapping("/save/orderBilling")
	public CommonResponse saveOrderBilling(@RequestBody OrderBillingRequest req) {
		return service.saveOrderBilling(req);
	}
	
	@PostMapping("/view/orderBilling")
	public CommonResponse viewOrderBilling(@RequestBody EditOrderDetailsReq req) {
		return service.viewOrderBilling(req);
	}
	
	@GetMapping("/getAll/orderBilling")
	public CommonResponse getAllOrderBilling() {
		return service.getAllOrderBilling();
	}
	
	
	@PostMapping("/calculateOrderBox")
	public CommonResponse calculateOrderBox(@RequestBody  BoxCalculateReq req) {
		CommonResponse response = new CommonResponse();
		try {
			Integer totalPieces = Integer.valueOf(req.getTotalPieces());
			Integer noOfPieces = Integer.valueOf(req.getNoOfPieces());
			
			double totalBoxes =totalPieces / noOfPieces;
			
			Map<String,String> res = new HashMap<String,String>();
			res.put("Response", String.valueOf(totalBoxes));
			
			response.setMessage("Success");
			response.setResponse(res);
			response.setError(null);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
}
