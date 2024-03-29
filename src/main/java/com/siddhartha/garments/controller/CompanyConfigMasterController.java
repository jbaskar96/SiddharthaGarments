package com.siddhartha.garments.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siddhartha.garments.dto.GetProductColorMetalReq;
import com.siddhartha.garments.dto.GetProductSizeColorReq;
import com.siddhartha.garments.dto.GetProductSizeMetalReq;
import com.siddhartha.garments.dto.ProductSizeColorRequest;
import com.siddhartha.garments.dto.ProductSizeMasterReq;
import com.siddhartha.garments.dto.ProductSizeMetalReq;
import com.siddhartha.garments.dto.SaveProductSizeColorMetalReq;
import com.siddhartha.garments.request.CompanyMasterRequest;
import com.siddhartha.garments.request.CompanyProductRequest;
import com.siddhartha.garments.request.ProductStyleMasterRequest;
import com.siddhartha.garments.response.CommonResponse;
import com.siddhartha.garments.response.GetProductMetalReq;
import com.siddhartha.garments.response.ProductMetalReq;
import com.siddhartha.garments.service.CompanyProductMasterService;


@RestController
@RequestMapping("/company")
public class CompanyConfigMasterController {
	
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
	
	@GetMapping("/getAll/style/{companyId}/{productId}")
	public CommonResponse getAllStyle(@PathVariable("companyId") Integer companyId,@PathVariable("productId") Integer productId) {
		return service.getAllStyle(companyId,productId);
	}
	
	@GetMapping("/edit/style/{companyId}/{productId}/{styleId}")
	public CommonResponse getAllStyle(@PathVariable("companyId") Integer companyId,@PathVariable("productId") Integer productId,
			@PathVariable("styleId") Integer styleId) {
		return service.getAllStyle(companyId,productId,styleId);
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
	
	
	@PostMapping("/save/size")
	public CommonResponse saveSize(@RequestBody List<ProductSizeMasterReq> req) {
		return service.saveSize(req);
	}
	
	@GetMapping("/getSizeDetailsByCompanyIdAndPrductId/{companyId}/{productId}")
	public CommonResponse getSizeDetailsByCompanyIdAndPrductId(@PathVariable("companyId") Integer companyId,@PathVariable
			("productId") Integer productId) {
		return service.getSizeDetailsByCompanyIdAndPrductId(companyId,productId);
	}
	
	@GetMapping("/getSizeDetailsByCompanyIdAndPrductId/{companyId}/{productId}/{sizeId}")
	public CommonResponse getSizeDetailsByCompanyIdAndPrductId(@PathVariable("companyId") Integer companyId,@PathVariable
			("productId") Integer productId,@PathVariable
			("sizeId") Integer sizeId) {
		return service.getSizeDetailsByCompanyIdAndPrductId(companyId,productId,sizeId);
	}
	
	@PostMapping("/save/size/metal")
	private CommonResponse saveSizeMetal(@RequestBody List<ProductSizeMetalReq> req) {
		return service.saveSizeMetal(req);
	}
	
	@PostMapping("/getSizeMetalDetails")
	public CommonResponse getSizeMetalDetails(@RequestBody GetProductSizeMetalReq req) {
		return service.getSizeMetalDetails(req);
	}
	
	@PostMapping("/editSizeMetalDetails")
	public CommonResponse editSizeMetalDetails(@RequestBody GetProductSizeMetalReq req) {
		return service.editSizeMetalDetails(req);
	}
	
	@PostMapping("/save/size/color")
	public CommonResponse saveSizeColor(@RequestBody List<ProductSizeColorRequest> req) {
		return service.saveSizeColor(req);
	}
	
	@PostMapping("/getSizeColorDetails")
	public CommonResponse getSizeColorDetails(@RequestBody GetProductSizeColorReq req) {
		return service.getSizeColorDetails(req);
	}
	
	@PostMapping("/editSizeColorDetails")
	public CommonResponse editSizeColorDetails(@RequestBody GetProductSizeColorReq req) {
		return service.editSizeColorDetails(req);
	}
	
	@PostMapping("/save/color/metal")
	public CommonResponse saveColorMetal(@RequestBody List<SaveProductSizeColorMetalReq> req) {
		return service.saveColorMetal(req);
	}
	
	@PostMapping("/getColorMetalDetails")
	public CommonResponse getColorMetalDetails(@RequestBody GetProductColorMetalReq req) {
		return service.getColorMetalDetails(req);
	}
	
	@PostMapping("/editColorMetalDetails")
	public CommonResponse editColorMetalDetails(@RequestBody GetProductColorMetalReq req) {
		return service.editColorMetalDetails(req);
	}
	
	@PostMapping("/save/product/metal")
	private CommonResponse saveProductMetal(@RequestBody List<ProductMetalReq> req) {
		return service.saveProductMetal(req);
	}
	
	@PostMapping("/getProductMetalDetails")
	public CommonResponse getProductMetalDetails(@RequestBody GetProductMetalReq req) {
		return service.getProductMetalDetails(req);
	}
	
	
}
