package com.siddhartha.garments.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class WorkerEntryDetailsReq {
	
	@JsonProperty("SerialNo")
	private String serialNo;
	
	@JsonProperty("OperatorId")
	private String operatorId;
	
	@JsonProperty("OperatorName")
	private String operatorName;
	
	@JsonProperty("SectionId")
	private String sectionId;
	
	@JsonProperty("SectionName")
	private String sectionName;
	
	@JsonProperty("OrderId")
	private String orderId;
	
	@JsonProperty("LotNumber")
	private String lotNumber;
	
	@JsonProperty("ChallanId")
	private String challanId;
	
	@JsonProperty("ChallanNumber")
	private String challanNumber;
	
	@JsonProperty("ColorId")
	private String colorId;
	
	@JsonProperty("ColorName")
	private String colorName;
	
	@JsonProperty("TotalPieces")
	private String totalPieces;
	
	@JsonProperty("WorkedPieces")
	private String workedPieces;
	
	@JsonProperty("DamagedPieces")
	private String damagedPieces;
	
	@JsonProperty("GoodPieces")
	private String goodPieces;
	
	@JsonProperty("UpdatedBy")
	private String updatedBy;
	
	@JsonProperty("Status")
	private String status;

	

}
