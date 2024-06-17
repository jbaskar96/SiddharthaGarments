package com.siddhartha.garments.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CompanyMeterialMasterReq {
	
	@JsonProperty("CompanyId")
	private String companyId;
	
	@JsonProperty("ProductId")
	private String productId;
	
	@JsonProperty("SizeId")
	private String sizeId;
	
	@JsonProperty("ColorId")
	private String colorId;
	
	@JsonProperty("MeterialId")
	private String meterialId;
	
	@JsonProperty("MeasurementName")
	private String measurementName;
	
	@JsonProperty("MeasurementType")
	private String measurementType;

	@JsonProperty("MeasurementValue")
	private String measurementValue;
	
	@JsonProperty("MeasurementPieces")
	private String measurementPieces;
	
	@JsonProperty("MeasurementDispalyName")
	private String measurementDispalyName;
	
	@JsonProperty("MeasurementDisplayOrder")
	private String measurementDisplayOrder;
	
	@JsonProperty("DBColumnName")
	private String dbColumnName;
	
	@JsonProperty("Status")
	private String status;

	
}
