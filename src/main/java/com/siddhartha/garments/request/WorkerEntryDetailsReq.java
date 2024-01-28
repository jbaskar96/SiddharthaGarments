package com.siddhartha.garments.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class WorkerEntryDetailsReq {
	
	@JsonProperty("SerialNo")
	private String serialNo;
	
	@JsonProperty("OperatorId")
	private String operatorId;
	
	@JsonProperty("SectionId")
	private String sectionId;
	
	@JsonProperty("OrderId")
	private String orderId;
	
	@JsonProperty("ChallanId")
	private String challanId;
	
	@JsonProperty("ColorId")
	private String colorId;
	
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
