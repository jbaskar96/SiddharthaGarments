package com.siddhartha.garments.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class LoginRequest {
	
	@JsonProperty("UserName")
	private String userName;

	@JsonProperty("Password")
	private String password;

}
