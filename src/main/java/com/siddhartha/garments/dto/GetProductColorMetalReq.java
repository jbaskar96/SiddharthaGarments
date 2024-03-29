package com.siddhartha.garments.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GetProductColorMetalReq {
	
	@JsonProperty("CompanyId")
	private String companyId;
	
	@JsonProperty("ProductId")
	private String productId;
	
	@JsonProperty("ColorCode")
	private String colorCode;
	
	@JsonProperty("MetalId")
	private String metalId;

}
