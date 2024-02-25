package com.siddhartha.garments.dto;

import java.util.Date;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ProductSizeMetalReq {
	
	@JsonProperty("CompanyId")
	private String companyId;
	
	@JsonProperty("ProductId")
	private String productId;
	
	@JsonProperty("SizeId")
	private String sizeId;
	
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
