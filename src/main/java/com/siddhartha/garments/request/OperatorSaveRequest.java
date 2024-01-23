package com.siddhartha.garments.request;

import java.util.Date;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperatorSaveRequest {

	@JsonProperty("OperatorId")
	private String operatorId;
	
	@JsonProperty("OperatorName")
	private String operatorName;
	
	@JsonProperty("MobileNo")
	private String mobileNo;
	
	@JsonProperty("AadharNo")
	private String aadharNo;
	
	@JsonProperty("Email")
	private String email;
	
	@JsonProperty("DateOfBirth")
	private String dateOfBirth;
	
	@JsonProperty("CreatedBy")
	private String createdBy;
	
	@JsonProperty("Status")
	private String status;
	
}