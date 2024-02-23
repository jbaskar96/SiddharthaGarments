package com.siddhartha.garments.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductStyleMasterRequest {
	
	@JsonProperty("CompanyId")
	private String companyId;
	
	@JsonProperty("ProductId")
	private String productId;
	
	@JsonProperty("StyleId")
	private String styleId;
	
	@JsonProperty("StyleName")
	private String styleName;
	
	@JsonProperty("Status")
	private String Status;
	
	@JsonProperty("Remarks")
	private String remarks;
	
	@JsonProperty("CreatedBy")
	private String createdBy;
	
	

}
