package com.siddhartha.garments.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siddhartha.garments.request.CompanyMeterialDropdownReq;
import com.siddhartha.garments.request.CompanyMeterialMasterReq;
import com.siddhartha.garments.response.CommonResponse;
import com.siddhartha.garments.service.CompanyMeterialMasterService;


@RestController
@RequestMapping("/meterial")
public class CompanyMeterialMasterController {
	
	
	@Autowired
	private CompanyMeterialMasterService service;
	
	
	@PostMapping("/save")
	public CommonResponse saveMeterial(@RequestBody List<CompanyMeterialMasterReq> req) {
		return service.saveMeterial(req);
	}

	@GetMapping("/getAllMeterial/{companyId}/{productId}")
	public CommonResponse getAllMeterialByCompanyId(@PathVariable("companyId") Integer companyId,@PathVariable("productId") Integer productId) {
		return service.getAllMeterialByCompanyId(companyId,productId);
	}
	
	@PostMapping("/product/dropdown")
	public CommonResponse productDropdown(@RequestBody CompanyMeterialDropdownReq req) {
		return service.productDropdown(req);
	}
	
	@PostMapping("/size/dropdown")
	public CommonResponse sizeDropdown(@RequestBody CompanyMeterialDropdownReq req) {
		return service.sizeDropdown(req);
	}
	
	@PostMapping("/color/dropdown")
	public CommonResponse colorDropdown(@RequestBody CompanyMeterialDropdownReq req) {
		return service.colorDropdown(req);
	}
}
