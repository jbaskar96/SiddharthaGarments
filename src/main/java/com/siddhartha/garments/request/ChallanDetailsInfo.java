package com.siddhartha.garments.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ChallanDetailsInfo {

	@JsonProperty("ChallanNumber")
	private String challanNumber;
	
	@JsonProperty("ChallanId")
	private String challanId;

	@JsonProperty("ChallanDate")
	private String challanDate;
	
	@JsonProperty("SizeId")
	private String sizeId;
	
	@JsonProperty("Size")
	private String size;

	@JsonProperty("TotalPieces")
	private String totalPieces;
	
	@JsonProperty("ColorFoldingDetails")
	private List<ColorFoldingDetailsReq> colorFoldingDetails;
}
