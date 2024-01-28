package com.siddhartha.garments.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class LotDetailsRequest {
	
	
	@JsonProperty("OrderId")
	private String orderId;
	
	@JsonProperty("LotNo")
	private String lotNo;
	
	@JsonProperty("ProductCode")
	private String productCode;
	
	@JsonProperty("CompanyName")
	private String companyName;
	
	@JsonProperty("StateCode")
	private String stateCode;
	
	@JsonProperty("DistrictCode")
	private String districtCode;
	
	@JsonProperty("City")
	private String city;
	
	@JsonProperty("Address")
	private String Address;
	
	@JsonProperty("PhoneNo")
	private String phoneNo;
	
	@JsonProperty("Status")
	private String status;
	
	@JsonProperty("InwardDate")
	private String inwardDate;
	
	@JsonProperty("UpdatedBy")
	private String updatedBy;
	
	@JsonProperty("GstNo")
	private String gstNo;
	
	@JsonProperty("ChallanInfo")
	private List<ChallanInfoRequest> challanInfo;

	
}
