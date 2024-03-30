package com.siddhartha.garments.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GetOrderSizeColorReq {
	
	@JsonProperty("OrderId")
	private String orderId;
	
	@JsonProperty("ChallanId")
	private String challanId;
	
	@JsonProperty("ColorId")
	private String colorId;
	
	@JsonProperty("IsUpdateColor")
	private String isUpdateColor;

}
