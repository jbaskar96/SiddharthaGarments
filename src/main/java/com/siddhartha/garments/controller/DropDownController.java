package com.siddhartha.garments.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siddhartha.garments.response.CommonResponse;
import com.siddhartha.garments.service.DropDownService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/dropdown")
@Api(tags = "DROPDOWN CONTROLLER" ,description = "API'S")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DropDownController {
	
	
	@Autowired
	private DropDownService service;
	
	
	@GetMapping("/section")
	public CommonResponse section() {
		return service.section();
	}
	
	@GetMapping("/product")
	public CommonResponse product() {
		return service.product();
	}
	
	@GetMapping("/usertype")
	public CommonResponse usertype() {
		return service.usertype();
	}
	
	@GetMapping("/color")
	public CommonResponse color() {
		return service.color();
	}
	
	@GetMapping("/state")
	public CommonResponse state() {
		return service.state();
	}
	
	@GetMapping("/district/{stateCode}")
	public CommonResponse district(@PathVariable("stateCode") String stateCode) {
		return service.district(stateCode);
	}

	@GetMapping("/operator")
	public CommonResponse operator() {
		return service.operator();
	}
	
	@GetMapping("/size")
	public CommonResponse size() {
		return service.size();
	}
	
	@GetMapping("/get/orderDetails")
	public CommonResponse getOrderDetails() {
		return service.getOrderDetails();
	}
	
	@GetMapping("/get/sizeDetails/{orderId}")
	public CommonResponse getSizeDetails(@PathVariable("orderId") String orderId) {
		return service.getSizeDetails(orderId);
	}
	
	@GetMapping("/get/colorDeatilsByOrderId/{orderId}/{sizeId}")
	public CommonResponse colorDeatilsByOrderId(@PathVariable("orderId") String orderId,@PathVariable("sizeId") String sizeId) {
		return service.colorDeatilsByOrderId(orderId,sizeId);
	}
	
	@GetMapping("/metalType")
	public CommonResponse metalType() {
		return service.metalType();
	}
	
	@GetMapping("/company")
	public CommonResponse getCompany() {
		return service.getCompany();
	}
	
	@GetMapping("/company/items/{companyId}")
	public CommonResponse getCompanyBrand(@PathVariable("companyId") String companyId) {
		return service.getCompanyBrand(companyId);
	}
	
	@GetMapping("/item/category/{companyId}/{itemId}")
	public CommonResponse getBrandCategory(@PathVariable("companyId") String companyId,@PathVariable("itemId") String itemId) {
		return service.getBrandCategory(companyId,itemId);
	}
	
	@GetMapping("/purchase/category")
	public CommonResponse getPurChaseCategory() {
		CommonResponse response = new CommonResponse();
		Map<String,Object> map1 =new HashMap<String,Object>();
		Map<String,Object> map2 =new HashMap<String,Object>();
		map1.put("1", "GST");
		map2.put("2", "NON-GST");
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(map1);
		list.add(map2);
		response.setError(null);
		response.setMessage("Success");
		response.setResponse(list);
		return response;
	}
	
	@GetMapping("/expensive/category")
	public CommonResponse getExpensiveCatgeory() {
		CommonResponse response = new CommonResponse();
		Map<String,Object> map1 =new HashMap<String,Object>();
		Map<String,Object> map2 =new HashMap<String,Object>();
		map1.put("1", "Thread");
		map2.put("2", "Button");
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(map1);
		list.add(map2);
		response.setError(null);
		response.setMessage("Success");
		response.setResponse(list);
		return response;
	}
	
	@GetMapping("/expensive/accountType")
	public CommonResponse getExpensiveAccountCatgeory() {
		CommonResponse response = new CommonResponse();
		Map<String,Object> map1 =new HashMap<String,Object>();
		Map<String,Object> map2 =new HashMap<String,Object>();
		map1.put("1", "CASH");
		map2.put("2", "GPAY");
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(map1);
		list.add(map2);
		response.setError(null);
		response.setMessage("Success");
		response.setResponse(list);
		return response;
	}
	
	
}
