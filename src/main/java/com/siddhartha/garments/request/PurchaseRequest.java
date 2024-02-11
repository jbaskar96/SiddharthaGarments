package com.siddhartha.garments.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PurchaseRequest {
	
	@JsonProperty("SerialNo")
	private String serialNo;
	
	@JsonProperty("CategoryId")
	private String categoryId;
	
	@JsonProperty("BillRefNo")
	private String billRefNo;
	
	@JsonProperty("SupplierName")
	private String supplierName;
	
	@JsonProperty("AmountBeforeTax")
	private String amountBeforeTax;
	
	@JsonProperty("CGSTTax")
	private String cGSTTax;
	
	@JsonProperty("SGSTTax")
	private String sgstTax;

	@JsonProperty("BillDate")
	private String billDate;
	
	@JsonProperty("Status")
	private String status;
	
	@JsonProperty("ProductDesc")
	private String productDesc;
	

}
