package com.siddhartha.garments.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SectionSaveRequest {
	
	
	@JsonProperty("CompanyId")
	private String companyId;
	
	@JsonProperty("ProductId")
	private String ProductId;
	
	@JsonProperty("SectionId")
	private String sectionId;
	
	@JsonProperty("SectionName")
	private String sectionName;
	
	@JsonProperty("NoOfPieces")
	private String noOfPieces;
	
	@JsonProperty("PieceAmount")
	private String pieceAmount;
	
	@JsonProperty("Status")
	private String status;
	

}
