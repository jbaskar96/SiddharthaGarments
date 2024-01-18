package com.siddhartha.garments.service;

import javax.servlet.http.HttpServletRequest;

import com.siddhartha.garments.request.LoginRequest;
import com.siddhartha.garments.response.CommonResponse;

public interface LoginService {

	CommonResponse login(LoginRequest request, HttpServletRequest servletRequest);

}
