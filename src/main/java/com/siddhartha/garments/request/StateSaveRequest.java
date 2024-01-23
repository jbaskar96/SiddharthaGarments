package com.siddhartha.garments.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class StateSaveRequest {
	
	@JsonProperty("StateCode")
	private String stateCode;
	
	@JsonProperty("StateName")
	private String stateName;
	
	@JsonProperty("Status")
	private String status;

}
