package com.siddhartha.garments.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DeliveryMoveRequest {
	
	@JsonProperty("OrderId")
	private String orderId;
	
	@JsonProperty("UpdatedBy")
	private String updatedBy;
	
	@JsonProperty("Remarks")
	private String remarks;

}
