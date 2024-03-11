package com.siddhartha.garments.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UpdateOrderStatusReq {
	
	@JsonProperty("OrderId")
	private String orderId;
	
	@JsonProperty("OrderStatus")
	private String orderStatus;

}
