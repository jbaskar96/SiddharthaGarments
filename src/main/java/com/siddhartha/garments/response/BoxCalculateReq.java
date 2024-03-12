package com.siddhartha.garments.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class BoxCalculateReq {
	
	@JsonProperty("TotalPieces")
	private String totalPieces;

	@JsonProperty("NoOfBoxesPerPieces")
	private String noOfBoxesPerPieces;
	
	@JsonProperty("NoOfPieces")
	private String noOfPieces;


}
