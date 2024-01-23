package com.siddhartha.garments.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ColorSaveRequest {
	
	@JsonProperty("ColorId")
	private String colorId;
	
	@JsonProperty("ColorName")
	private String colorName;
	
	@JsonProperty("Status")
	private String status;

}
