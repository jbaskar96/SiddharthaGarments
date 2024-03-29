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
	
	@JsonProperty("NoOfBoxesPerPieces")
	private String noOfBoxesPerPieces;
	
	@JsonProperty("NoOfPieces")
	private String noOfPieces;
	
	@JsonProperty("TotalBox")
	private String totalBox;
	
	@JsonProperty("Remarks")
	private String remarks;
	
	@JsonProperty("UpdatedBy")
	private String updatedBy;
	

}
