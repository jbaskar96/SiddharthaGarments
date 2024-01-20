package com.siddhartha.garments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siddhartha.garments.service.SectionMasterService;

@RestController
@RequestMapping("/section")
public class SectionMasterController {
	
	@Autowired
	private SectionMasterService service;

}
