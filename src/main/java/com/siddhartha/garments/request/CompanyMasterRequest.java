package com.siddhartha.garments.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CompanyMasterRequest {
	
	
	@JsonProperty("CompanyId")
	private String companyId;
	
	@JsonProperty("CompanyName")
	private String companyName;
	
	@JsonProperty("State")
	private String state;
	
	@JsonProperty("Address")
	private String address;
	
	@JsonProperty("District")
	private String district;
	
	@JsonProperty("GstNumber")
	private String gstNumber;
	
	@JsonProperty("Status")
	private String status;
	
	@JsonProperty("CreatedBy")
	private String createdBy;
	
	@JsonProperty("MobileNo")
	private String mobileNo;
	
	@JsonProperty("Remarks")
	private String remarks;

}
