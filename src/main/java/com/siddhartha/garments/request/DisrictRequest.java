package com.siddhartha.garments.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DisrictRequest {
	
	@JsonProperty("StateCode")
	private String stateCode;
	
	@JsonProperty("DistrictCode")
	private String districtCode;
	
	@JsonProperty("DistrictName")
	private String districtName;
	
	@JsonProperty("Status")
	private String status;
	
	

}
