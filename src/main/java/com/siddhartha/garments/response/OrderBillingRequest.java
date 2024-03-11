package com.siddhartha.garments.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class OrderBillingRequest {
	
	@JsonProperty("OrderId")
	private String orderId;
	
	@JsonProperty("TotalPieces")
	private String totalPieces;
	
	@JsonProperty("DeliveryType")
	private String deliveryType;
	
	@JsonProperty("NoOfBoxOrDozens")
	private String noOfBoxOrDozens;
	
	@JsonProperty("NoOfPieces")
	private String noOfPieces;
	
	@JsonProperty("TotalBox")
	private String totalBox;
	
	@JsonProperty("Status")
	private String status;
	
	@JsonProperty("Remarks")
	private String remarks;
	
	@JsonProperty("UpdatedBy")
	private String updatedBy;
	

}
