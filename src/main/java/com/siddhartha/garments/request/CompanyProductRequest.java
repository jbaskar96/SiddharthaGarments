package com.siddhartha.garments.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CompanyProductRequest {
	
	
	@JsonProperty("CompanyId")
	private String companyId;
	
	@JsonProperty("CreatedBy")
	private String createdBy;
	
	@JsonProperty("ProductId")
	private String productId;
	
	@JsonProperty("ProductName")
	private String productName;
	
	@JsonProperty("Remarks")
	private String remarks;
	
	@JsonProperty("FoldingYn")
	private String foldingYn;
	
	@JsonProperty("FoldingMesurementType")
	private String foldingMesurementType;
	
	@JsonProperty("FoldingMesurementValue")
	private String foldingMesurementValue;
	
	@JsonProperty("ElasticYn")
	private String elasticYn;
	
	@JsonProperty("ElasticMesurementType")
	private String elasticMesurementType;
	
	@JsonProperty("ElasticMesurementValue")
	private String elasticMesurementValue;
	
	@JsonProperty("Status")
	private String status;
	


}
