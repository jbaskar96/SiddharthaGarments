package com.siddhartha.garments.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UserTypeRequest {
	
	
	@JsonProperty("UserTypeId")
	private String userTypeId;
	
	@JsonProperty("UserTypeName")
	private String userTypeName;
	
	@JsonProperty("Status")
	private String status;

}
