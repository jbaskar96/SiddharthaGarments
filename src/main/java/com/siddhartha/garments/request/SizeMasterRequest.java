package com.siddhartha.garments.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SizeMasterRequest {
	
	@JsonProperty("SizeId")
	private String sizeId;
	
	@JsonProperty("Size")
	private String size;
	
	@JsonProperty("Status")
	private String status;

}
