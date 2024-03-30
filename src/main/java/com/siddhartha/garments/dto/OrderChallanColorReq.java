package com.siddhartha.garments.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class OrderChallanColorReq {
	
	@JsonProperty("OrderId")
	private String orderId;
	
	@JsonProperty("ColorId")
	private String colorId;
	
	@JsonProperty("ChallanId")
	private String ChallanId;
	
	@JsonProperty("ColorCode")
	private String colorCode;
	
	@JsonProperty("ColorName")
	private String colorName;
	
	@JsonProperty("TotalPieces")
	private String totalPieces;
	

}
