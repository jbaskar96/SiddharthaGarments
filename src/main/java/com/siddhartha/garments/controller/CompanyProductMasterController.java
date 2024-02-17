package com.siddhartha.garments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siddhartha.garments.request.CompanyMasterRequest;
import com.siddhartha.garments.request.CompanyProductRequest;
import com.siddhartha.garments.request.ProductStyleMasterRequest;
import com.siddhartha.garments.response.CommonResponse;
import com.siddhartha.garments.service.CompanyProductMasterService;


@RestController
@RequestMapping("/company")
public class CompanyProductMasterController {
	
	@Autowired
	private CompanyProductMasterService service;
	
	@PostMapping("/save")
	public CommonResponse companySave(@RequestBody CompanyMasterRequest req) {
		return service.companySave(req);
	}
	
	@PostMapping("/product/save")
	public CommonResponse productSave(@RequestBody CompanyProductRequest req) {
		return service.productSave(req);
	}

	@PostMapping("/style/save")
	public CommonResponse styleSave(@RequestBody ProductStyleMasterRequest req) {
		return service.styleSave(req);
	}

	@GetMapping("/getAllCompany")
	public CommonResponse getAllCompany() {
		return service.getAllCompany();
	}
	
	@GetMapping("/edit/company/{companyId}")
	public CommonResponse editCompany(@PathVariable("companyId") Integer companyId) {
		return service.editCompany(companyId);
	}
	
	@GetMapping("/getAllProductByCompanyId/{companyId}")
	public CommonResponse getAllProductByCompanyId(@PathVariable("companyId") Integer companyId) {
		return service.getAllProductByCompanyId(companyId);
	}
	
	@GetMapping("/editProductByCompanyIdAndProductId/{companyId}/{productId}")
	public CommonResponse editProductByCompanyIdAndProductId(@PathVariable("companyId") Integer companyId,@PathVariable("productId") Integer productId) {
		return service.editProductByCompanyIdAndProductId(companyId,productId);
	}
	
	
	
}
