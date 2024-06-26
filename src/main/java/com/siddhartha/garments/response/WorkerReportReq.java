package com.siddhartha.garments.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class WorkerReportReq {
	
	@JsonProperty("StartDate")
	private String startDate;
	
	@JsonProperty("EndDate")
	private String endDate;
	
	@JsonProperty("OperatorId")
	private String operatorId;

}
