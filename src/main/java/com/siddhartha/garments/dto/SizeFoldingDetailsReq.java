package com.siddhartha.garments.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SizeFoldingDetailsReq {
	
	@JsonProperty("Required")
	private List<String> required;
	
	@JsonProperty("Received")
	private List<String> received;
	
	@JsonProperty("ChallanDate")
	private String ChallanDate;
	
	@JsonProperty("Size")
	private String Size;
	
	@JsonProperty("TotalPieces")
	private String TotalPieces;
	
	@JsonProperty("ChallanId")
	private String ChallanId;
	
	@JsonProperty("ChallanNumber")
	private String challanNumber;
	
	@JsonProperty("SizeId")
	private String SizeId;
	
	@JsonProperty("MetalName")
	private List<String> metalName;
	
	
}
