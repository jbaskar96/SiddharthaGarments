package com.siddhartha.garments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siddhartha.garments.request.DisrictRequest;
import com.siddhartha.garments.response.CommonResponse;
import com.siddhartha.garments.service.DistrictMasterService;

@RestController
@RequestMapping("/district")
public class DistrictMasterController {
	
	@Autowired
	private DistrictMasterService service;
	

	@PostMapping("/save")
	public CommonResponse saveDistrict(@RequestBody DisrictRequest req) {
		return service.saveDistrict(req);
	}
	
	@GetMapping("/getAll/{statecode}")
	public CommonResponse getAllDistrict(@PathVariable("statecode") Integer statecode) {
		return service.getAllDistrict(statecode);
	}
	
	@GetMapping("/editById/{statecode}/{districtcode}")
	public CommonResponse editDistrict(@PathVariable("statecode") Integer stateCode,
			@PathVariable("districtcode") Integer districtCode) {
		return service.getAllDistrict(stateCode,districtCode);
	}
	
	@GetMapping("/deleteById/{statecode}/{districtcode}")
	public CommonResponse deleteDistrict(@PathVariable("statecode") Integer stateCode,
			@PathVariable("districtcode") Integer districtCode) {
		return service.deleteDistrict(stateCode,districtCode);
	}

}
