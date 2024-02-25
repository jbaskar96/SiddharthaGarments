package com.siddhartha.garments.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class OrderChallanInfoReq {
	
	@JsonProperty("ChallanId")
	private String challanId;

	@JsonProperty("ChallanNumber")
	private String challanNumber;
	
	@JsonProperty("ChallanDate")
	private String challanDate;
	
	@JsonProperty("SizeId")
	private String sizeId;
	
	@JsonProperty("Size")
	private String size;
	
	@JsonProperty("TotalPieces")
	private String totalPieces;
	
	

}
