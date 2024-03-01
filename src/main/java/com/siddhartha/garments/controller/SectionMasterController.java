package com.siddhartha.garments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siddhartha.garments.request.SectionSaveRequest;
import com.siddhartha.garments.response.CommonResponse;
import com.siddhartha.garments.service.SectionMasterService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/section")
@Api(tags = "MASTER : SECTION CONTROLLER",description = "API'S")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SectionMasterController {
	
	@Autowired
	private SectionMasterService service;
	
	
	@PostMapping("/save")
	public CommonResponse saveSection(@RequestBody SectionSaveRequest req) {
		return service.saveSection(req);
	}
	
	@GetMapping("/getAll")
	public CommonResponse getAllSection() {
		return service.getAllSection();
	}
	
	@GetMapping("/edit/{sectionId}")
	public CommonResponse editSection(@PathVariable("sectionId") Integer sectionId) {
		return service.editSection(sectionId);
	}
	
	@GetMapping("/delete/{sectionId}")
	public CommonResponse deleteSection(@PathVariable("sectionId") Integer sectionId) {
		return service.deleteSection(sectionId);
	}

}
