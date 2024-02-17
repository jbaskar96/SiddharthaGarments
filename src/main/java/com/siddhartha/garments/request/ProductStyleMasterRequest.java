package com.siddhartha.garments.request;

import java.util.List;

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
	
	@JsonProperty("CreatedBy")
	private String createdBy;
	
	@JsonProperty("ProductStyleList")
	private List<ProductStyleList> productStyleList;

}
