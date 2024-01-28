package com.siddhartha.garments.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class MeterialDetailsReq {
	
	@JsonProperty("OrderId")
	private String orderId;
	
	@JsonProperty("ChallanNo")
	private String challanNo;


}
