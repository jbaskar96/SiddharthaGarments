package com.siddhartha.garments.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductStyleList {
	
	@JsonProperty("StyleId")
	private String styleId;
	
	@JsonProperty("StyleName")
	private String styleName;
	
	@JsonProperty("Remarks")
	private String remarks;
	
	@JsonProperty("Status")
	private String status;

}
