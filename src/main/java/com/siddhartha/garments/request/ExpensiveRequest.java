package com.siddhartha.garments.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ExpensiveRequest {
	
	@JsonProperty("SerialNo")
	private String serialNo;

	@JsonProperty("AccountType")
	private String accountType;

	@JsonProperty("CategoryType")
	private String categoryType;

	@JsonProperty("Amount")
	private String amount;

	@JsonProperty("Notes")
	private String notes;

	@JsonProperty("Status")
	private String status;
	
	@JsonProperty("ExpensiveDate")
	private String expensiveDate;

	
	

}
