package com.siddhartha.garments.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PurchaseReportReq {
	
	@JsonProperty("StartDate")
	private String startDate;
	
	@JsonProperty("EndDate")
	private String endDate;

	@JsonProperty("ReportType")
	private String reportType;

	

}
