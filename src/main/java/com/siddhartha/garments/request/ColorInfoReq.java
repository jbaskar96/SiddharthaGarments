package com.siddhartha.garments.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ColorInfoReq {
	
	@JsonProperty("ColorId")
	private String colorId;
	
	@JsonProperty("ColorCode")
	private String colorCode;
	
	@JsonProperty("NoOfBundles")
	private String noOfBundles;
	
	@JsonProperty("TotalDozen")
	private String totalDozen;
	
	@JsonProperty("RequiredFoldingWeight")
	private String requiredFoldingWeight;
	
	@JsonProperty("ReceivedFoldingWeight")
	private String receivedFoldingWeigth;
	
	@JsonProperty("RequiredElastic")
	private String requiredElastic;
	
	@JsonProperty("ReceviedElastic")
	private String receivedElastic;
	
	@JsonProperty("PiecesCount")
	private String piecesCount;
	
	@JsonProperty("Status")
	private String status;
	
	@JsonProperty("Remarks")
	private String remarks;
	
	
	

}
