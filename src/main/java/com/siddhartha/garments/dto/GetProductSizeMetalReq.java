package com.siddhartha.garments.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GetProductSizeMetalReq {
	
	@JsonProperty("CompanyId")
	private String companyId;
	
	@JsonProperty("ProductId")
	private String productId;
	
	@JsonProperty("SizeId")
	private String sizeId;
	
	@JsonProperty("MetalId")
	private String metalId;
	

}
