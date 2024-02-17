package com.siddhartha.garments.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CompanyProductRequest {
	
	
	@JsonProperty("CompanyId")
	private String companyId;
	
	@JsonProperty("CreatedBy")
	private String createdBy;
	
	@JsonProperty("CompanyProductList")
	private List<CompanyProductList> companyProductList;

}
