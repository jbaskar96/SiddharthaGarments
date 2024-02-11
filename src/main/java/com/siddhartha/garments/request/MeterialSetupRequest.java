package com.siddhartha.garments.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class MeterialSetupRequest {
	
	@JsonProperty("ProductId")
	private String productId;

	@JsonProperty("SetupId")
	private String setupId;
	
	@JsonProperty("TableColumn")
	private String tableColumn;
	
	@JsonProperty("DisplayColumn")
	private String displayColumn;
	
	@JsonProperty("DisplayOrder")
	private String displayOrder;
	
	@JsonProperty("QuantityType")
	private String quantityType;
	
	@JsonProperty("CalcType")
	private String calcType;
	
	@JsonProperty("CalcPercentage")
	private String calcPercentage;
	
	@JsonProperty("CalcAmount")
	private String calcAmount;
	
	@JsonProperty("BoxPieceCount")
	private String boxPieceCount;
	
	@JsonProperty("Status")
	private String status;
	

}
