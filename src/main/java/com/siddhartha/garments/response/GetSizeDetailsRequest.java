package com.siddhartha.garments.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GetSizeDetailsRequest {
	
	@JsonProperty("OrderId")
	private String orderId;
	
	@JsonProperty("ChallanId")
	private String challanId;

}
