package com.siddhartha.garments.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siddhartha.garments.dto.EditOrderDetailsReq;
import com.siddhartha.garments.response.CommonResponse;
import com.siddhartha.garments.response.GetSizeDetailsRequest;
import com.siddhartha.garments.service.DropDownService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/dropdown")
@Api(tags = "DROPDOWN CONTROLLER" ,description = "API'S")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DropDownController {
	
	
	@Autowired
	private DropDownService service;
	
	
	@PostMapping("/section")
	public CommonResponse section(@RequestBody EditOrderDetailsReq req) {
		return service.section(req);
	}
	
	@GetMapping("/product")
	public CommonResponse product() {
		return service.product();
	}
	
	@GetMapping("/usertype")
	public CommonResponse usertype() {
		CommonResponse response = new CommonResponse();
		Map<String,Object> map1 =new HashMap<String,Object>();
		Map<String,Object> map2 =new HashMap<String,Object>();
		Map<String,Object> map3 =new HashMap<String,Object>();
		
		map1.put("1", "Admin");
		map2.put("2", "User");
		map3.put("3", "Operator");
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(map1);
		list.add(map2);
		list.add(map3);
		
		response.setError(null);
		response.setMessage("Success");
		response.setResponse(list);
		return response;
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
	
	@PostMapping("/get/sizeDetails")
	public CommonResponse size(@RequestBody GetSizeDetailsRequest req) {
		return service.size(req);
	}
	
	@GetMapping("/order")
	public CommonResponse getOrderDetails() {
		return service.getOrderDetails();
	}
	
	//@GetMapping("/get/sizeDetails/{orderId}")
	public CommonResponse getSizeDetails(@PathVariable("orderId") String orderId) {
		return service.getSizeDetails(orderId);
	}
	
	@PostMapping("/get/colorDeatilsByOrderId")
	public CommonResponse colorDeatilsByOrderId(@RequestBody GetSizeDetailsRequest req) {
		return service.colorDeatilsByOrderId(req);
	}
	
	@GetMapping("/metalType")
	public CommonResponse metalType() {
		return service.metalType();
	}
	
	@GetMapping("/company")
	public CommonResponse getCompany() {
		return service.getCompany();
	}
	
	@GetMapping("/company/product/{companyId}")
	public CommonResponse getProduct(@PathVariable("companyId") Integer companyId) {
		return service.getProduct(companyId);
	}
	
	@GetMapping("/company/product/size/{companyId}/{productId}")
	public CommonResponse getProductSize(@PathVariable("companyId") Integer companyId,@PathVariable("productId") Integer productId) {
		return service.getProductSize(companyId,productId);
	}
	
	@GetMapping("/company/product/size/color/{companyId}/{productId}/{sizeId}")
	public CommonResponse getProductSizeColor(@PathVariable("companyId") Integer companyId,@PathVariable("productId") Integer productId,
			@PathVariable("sizeId") Integer sizeId) {
		return service.getProductSizeColor(companyId,productId,sizeId);
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
		map2.put("2", "Online");
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(map1);
		list.add(map2);
		response.setError(null);
		response.setMessage("Success");
		response.setResponse(list);
		return response;
	}
	
	@GetMapping("/sizeType")
	public CommonResponse sizeType() {
		return service.sizeType();
	}
	
	
	@GetMapping("/order/reportTypes")
	public CommonResponse getOrderReportTypes() {
		CommonResponse response = new CommonResponse();
		Map<String,Object> map1 =new HashMap<String,Object>();
		Map<String,Object> map2 =new HashMap<String,Object>();
		Map<String,Object> map3 =new HashMap<String,Object>();
		Map<String,Object> map4 =new HashMap<String,Object>();
		Map<String,Object> map5 =new HashMap<String,Object>();
		map1.put("Y", "Inward");
		map2.put("P", "Production");
		map3.put("D", "Delivered");
		map4.put("B", "Billing");
		map5.put("ALL", "ALL");
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(map1);
		list.add(map2);
		list.add(map3);
		list.add(map4);
		list.add(map5);
		response.setError(null);
		response.setMessage("Success");
		response.setResponse(list);
		return response;
	}
	
	@GetMapping("/worker/reportTypes")
	public CommonResponse getWorkerReportTypes() {
		CommonResponse response = new CommonResponse();
		Map<String,Object> map1 =new HashMap<String,Object>();
		Map<String,Object> map2 =new HashMap<String,Object>();
		Map<String,Object> map3 =new HashMap<String,Object>();
		Map<String,Object> map4 =new HashMap<String,Object>();
		map1.put("OPT", "OperatorId");
		map2.put("O", "OrderId");
		map3.put("Date", "Entrydate");
		map4.put("S", "Section");
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(map1);
		list.add(map2);
		list.add(map3);
		list.add(map4);
		response.setError(null);
		response.setMessage("Success");
		response.setResponse(list);
		return response;
	}
	
	@GetMapping("/purchase/reportTypes")
	public CommonResponse getPurchaseReportTypes() {
		CommonResponse response = new CommonResponse();
		Map<String,Object> map1 =new HashMap<String,Object>();
		Map<String,Object> map2 =new HashMap<String,Object>();
		Map<String,Object> map3 =new HashMap<String,Object>();
		map1.put("1", "GST");
		map2.put("2", "NON-GST");
		map3.put("ALL", "ALL");
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(map1);
		list.add(map2);
		list.add(map3);
		response.setError(null);
		response.setMessage("Success");
		response.setResponse(list);
		return response;
	}
	
	@GetMapping("/expensive/category/reportTypes")
	public CommonResponse getExpensiveCatgeoryReportTypes() {
		CommonResponse response = new CommonResponse();
		Map<String,Object> map1 =new HashMap<String,Object>();
		Map<String,Object> map2 =new HashMap<String,Object>();
		Map<String,Object> map3 =new HashMap<String,Object>();
		map1.put("1", "Thread");
		map2.put("2", "Button");
		map3.put("ALL", "ALL");
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(map1);
		list.add(map2);
		list.add(map3);
		response.setError(null);
		response.setMessage("Success");
		response.setResponse(list);
		return response;
	}
	
	@GetMapping("/expensive/accountType/reportTypes")
	public CommonResponse getExpensiveAccountTypeReportTypes() {
		CommonResponse response = new CommonResponse();
		Map<String,Object> map1 =new HashMap<String,Object>();
		Map<String,Object> map2 =new HashMap<String,Object>();
		Map<String,Object> map3 =new HashMap<String,Object>();
		map1.put("1", "Thread");
		map2.put("2", "Button");
		map3.put("ALL", "ALL");
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(map1);
		list.add(map2);
		list.add(map3);
		response.setError(null);
		response.setMessage("Success");
		response.setResponse(list);
		return response;
	}
	
	@PostMapping("/order/sizes")
	public CommonResponse orderSizes(@RequestBody EditOrderDetailsReq req) {
		return service.orderSizes(req);
	}
	
	@GetMapping("/getColorDetails/{companyId}/{productId}")
	public CommonResponse getColorDetails(Integer companyId,Integer productId) {
		return service.getColorDetails(companyId,productId);
	}
	
}
