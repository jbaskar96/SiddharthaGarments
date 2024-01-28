package com.siddhartha.garments.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChallanInfoRequest {
	
	@JsonProperty("ChallanId")
	private String challanId;
	
	@JsonProperty("ChallanNo")
	private String challanNo;
	
	@JsonProperty("PieceYn")
	private String pieceYn;
	
	@JsonProperty("TotalPieces")
	private String totalPieces;
	
	@JsonProperty("ChallanDate")
	private String challanDate;
	
	@JsonProperty("Size")
	private String size;
	
	@JsonProperty("Quality")
	private String quality;
	
	@JsonProperty("Status")
	private String status;
	
}
