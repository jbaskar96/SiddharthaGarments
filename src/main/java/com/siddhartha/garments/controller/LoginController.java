package com.siddhartha.garments.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siddhartha.garments.request.ErrorList;
import com.siddhartha.garments.request.LoginRequest;
import com.siddhartha.garments.response.CommonResponse;
import com.siddhartha.garments.service.LoginService;
import com.siddhartha.garments.serviceImpl.InputValidationServiceImpl;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/authentication")
@Api(tags = "LOGIN CONTROLLER",description = "API'S")
public class LoginController {
	
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private InputValidationServiceImpl inputValidationService;
	
	
	@PostMapping("/login")
	public CommonResponse login(@RequestBody LoginRequest request,HttpServletRequest servletRequest) {
		CommonResponse response = new CommonResponse();
		List<ErrorList> error =inputValidationService.validateLoginRequest(request);
		if(error.isEmpty()) {
			response =loginService.login(request,servletRequest);	
		}else {
			response.setError(error);
			response.setMessage("Error");
			response.setResponse(null);
		}
		return response;
	}
	

}
