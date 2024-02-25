package com.siddhartha.garments.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class EditOrderDetailsReq {
	
	@JsonProperty("OrderId")
	private String orderId;

}
