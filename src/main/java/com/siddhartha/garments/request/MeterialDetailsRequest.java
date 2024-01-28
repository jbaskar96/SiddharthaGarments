package com.siddhartha.garments.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class MeterialDetailsRequest {
	
	@JsonProperty("OrderId")
	private String orderId;
	
	@JsonProperty("ChallanId")
	private String challanId;
	
	@JsonProperty("MeterialInfo")
	private List<MeterialInfoReq> meterialInfo;

	
}
