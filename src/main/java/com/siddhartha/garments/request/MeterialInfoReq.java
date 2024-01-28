package com.siddhartha.garments.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class MeterialInfoReq {
	
	@JsonProperty("MeterialId")
	private String meterialId;
	
	@JsonProperty("MetalType")
	private String metalType;
	
	@JsonProperty("Lable")
	private String lable;
	
	@JsonProperty("SizeSticker")
	private String sizeSticker;
	
	@JsonProperty("InnerCard")
	private String innercard;
	
	@JsonProperty("PolyBag")
	private String polyBag;
	
	@JsonProperty("SingleBox")
	private String singleBox;
	
	@JsonProperty("MasterBox")
	private String masterBox;
	
	@JsonProperty("MrpSticker")
	private String mrpSticker;
	
	@JsonProperty("BodySticker")
	private String bodySticker;
	
	@JsonProperty("HoloGramSticker")
	private String holoGramSticker;
	
	@JsonProperty("Status")
	private String status;
	
	@JsonProperty("Remarks")
	private String remarks;

}
