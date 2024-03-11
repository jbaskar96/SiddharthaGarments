package com.siddhartha.garments.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UpdateOrderStatusReq {
	
	@JsonProperty("OrderId")
	private List<String> orderId;
	
	@JsonProperty("OrderStatus")
	private String orderStatus;

}
