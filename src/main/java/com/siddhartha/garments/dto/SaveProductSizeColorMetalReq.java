package com.siddhartha.garments.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SaveProductSizeColorMetalReq {
	
	@JsonProperty("CompanyId")
	private String companyId;
	
	@JsonProperty("ProductId")
	private String productId;
	
	@JsonProperty("SizeId")
	private String sizeId;
	
	@JsonProperty("ColorCode")
	private String colorCode;
	
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

}
