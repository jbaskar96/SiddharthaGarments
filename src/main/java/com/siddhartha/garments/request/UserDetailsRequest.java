package com.siddhartha.garments.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Setter;

@Data
public class UserDetailsRequest {

	@JsonProperty("UserId")
	private String userId;
	
	@JsonProperty("FirstName")
	private String firstname;
	
	@JsonProperty("LastName")
	private String lastName;
	
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
	
	@JsonProperty("UserType")
	private String userType;
	
	@JsonProperty("UserName")
	private String userName;
	
	@JsonProperty("Password")
	private String password;
	
	@JsonProperty("StateCode")
	private String stateCode;
	
	@JsonProperty("DistrictCode")
	private String districtCode;
	
	@JsonProperty("City")
	private String city;
	
	@JsonProperty("Address")
	private String address;
	
	@JsonProperty("EditYn")
	private String editYn;
	
}
