package com.siddhartha.garments.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ColorFoldingDetailsReq {
	
	@JsonProperty("Required")
	private List<String> required;
	
	@JsonProperty("Received")
	private List<String> received;
	
	@JsonProperty("MetalName")
	private List<String> metalName;
	
	@JsonProperty("ColorId")
	private String colorId;
	
	@JsonProperty("ColorName")
	private String colorName;
	
	@JsonProperty("TotalPieces")
	private String totalPieces;

}
