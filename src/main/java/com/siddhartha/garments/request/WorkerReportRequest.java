package com.siddhartha.garments.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class WorkerReportRequest {
	
	@JsonProperty("OrderId")
	private String orderId;
	
	@JsonProperty("SizeId")
	private String sizeId;
	
	@JsonProperty("ColorId")
	private String colorId;
	
	@JsonProperty("SectionId")
	private String sectionId;
	
	@JsonProperty("OperatorId")
	private String operatorId;
	
	@JsonProperty("StartDate")
	private String startDate;
	
	@JsonProperty("EndDate")
	private String endDate;
	
	@JsonProperty("Type")
	private String type;
	
	

}
