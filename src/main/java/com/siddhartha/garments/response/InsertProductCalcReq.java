package com.siddhartha.garments.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InsertProductCalcReq {
	
	@JsonProperty("Required")
	private List<String> Required;
	
	@JsonProperty("Received")
	private List<String> Received;
	
	@JsonProperty("MetalName")
	private List<String> MetalName;
	
	@JsonProperty("TotalPieces")
	private String TotalPieces;
	
	@JsonProperty("OrderId")
	private String OrderId;
	
	@JsonProperty("CompanyId")
	private String CompanyId;
	
	@JsonProperty("ProductId")
	private String ProductId;


}
