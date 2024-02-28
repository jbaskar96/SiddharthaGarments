package com.siddhartha.garments.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InsertSizeCalcRequest {
	
	@JsonProperty("OrderId")
	private String orderId;
	
	@JsonProperty("CompanyId")
	private String companyId;
	
	@JsonProperty("ProductId")
	private String productId;
	
	@JsonProperty("SizeFoldingDetails")
	private List<SizeFoldingDetailsReq> sizeFoldingDetails;
	
}
