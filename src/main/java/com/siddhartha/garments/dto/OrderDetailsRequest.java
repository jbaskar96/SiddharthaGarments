package com.siddhartha.garments.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class OrderDetailsRequest {
	
	@JsonProperty("OrderId")
	private String orderId;
	
	@JsonProperty("CompanyId")
	private String companyId;
	
	@JsonProperty("ProductId")
	private String productId;
	
	@JsonProperty("StyleId")
	private String styleId;
	
	@JsonProperty("LotNumber")
	private String lotNumber;
	
	@JsonProperty("InwardDate")
	private String inwardDate;
	
	@JsonProperty("Status")
	private String status;
	
	@JsonProperty("Remarks")
	private String remarks;
	
	@JsonProperty("CreatedBy")
	private String createdBy;
	
	@JsonProperty("OrderChallanInfo")
	private List<OrderChallanInfoReq> orderChallanInfo;
	
}
