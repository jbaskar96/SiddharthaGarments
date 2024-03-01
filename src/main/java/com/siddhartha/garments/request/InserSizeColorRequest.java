package com.siddhartha.garments.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class InserSizeColorRequest {
	
	@JsonProperty("OrderId")
	private String orderId;
	
	@JsonProperty("CompanyId")
	private String companyId;

	@JsonProperty("ProductId")
	private String productId;

	@JsonProperty("ChallanDetails")
	private List<ChallanDetailsInfo> challanDetails;
	

}
