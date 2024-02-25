package com.siddhartha.garments.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ProductSizeMasterReq {
	
	@JsonProperty("CompanyId")
	private String companyId;
	
	@JsonProperty("ProductId")
	private String productId;
	
	@JsonProperty("SizeType")
	private String sizeType;
	
	@JsonProperty("SizeId")
	private String sizeId;
	
	@JsonProperty("Size")
	private String size;
	
	@JsonProperty("Status")
	private String status;
	
	@JsonProperty("Remarks")
	private String remarks;

}
