package com.siddhartha.garments.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ColorDetailsRequest {
	
	@JsonProperty("OrderId")
	private String orderId;
	
	@JsonProperty("ChallanId")
	private String challanId;
	
	@JsonProperty("ColorInfo")
	private List<ColorInfoReq> colorInfo;

}
