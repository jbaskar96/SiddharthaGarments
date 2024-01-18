package com.siddhartha.garments.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siddhartha.garments.request.LoginRequest;
import com.siddhartha.garments.response.CommonResponse;
import com.siddhartha.garments.service.LoginService;

@RestController
@RequestMapping("/authentication")
public class LoginController {
	
	
	@Autowired
	private LoginService loginService;
	
	
	@PostMapping("/login")
	public CommonResponse login(@RequestBody LoginRequest request,HttpServletRequest servletRequest) {
		return loginService.login(request,servletRequest);
	}
	

}
