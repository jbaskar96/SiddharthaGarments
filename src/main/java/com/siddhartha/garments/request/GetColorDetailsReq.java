package com.siddhartha.garments.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GetColorDetailsReq {
	
	@JsonProperty("OrderId")
	private String orderId;
	
	@JsonProperty("ChallanNo")
	private String challanNo;

}
