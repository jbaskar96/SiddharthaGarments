package com.siddhartha.garments.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ProductSaveRequest {
	
	@JsonProperty("ProductId")
	private String productId;
	
	@JsonProperty("ProductName")
	private String productName;

	@JsonProperty("Status")
	private String status;


}
