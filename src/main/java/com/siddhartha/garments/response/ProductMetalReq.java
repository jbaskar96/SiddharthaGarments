package com.siddhartha.garments.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductMetalReq {
	
	@JsonProperty("CompanyId")
	private String companyId;
	
	@JsonProperty("ProductId")
	private String productId;
	
	@JsonProperty("MetalId")
	private String metalId;
	
	@JsonProperty("Status")
	private String status;
	
	@JsonProperty("Remarks")
	private String remarks;
	
	@JsonProperty("MetalName")
	private String metalName;
	
	@JsonProperty("ColumnName")
	private String columnName;
	
	@JsonProperty("MesurementType")
	private String mesurementType;
	
	@JsonProperty("MesurementValue")
	private String mesurementValue;
	
	@JsonProperty("MesurementPieces")
	private String mesurementPieces;
	
	@JsonProperty("DisplayOrder")
	private String displayOrder;
	
	@JsonProperty("MesurementName")
	private String mesurementName;
	

}
