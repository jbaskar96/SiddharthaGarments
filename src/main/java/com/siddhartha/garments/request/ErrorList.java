package com.siddhartha.garments.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorList {

	
	@JsonProperty("ErrorField")
	private String errorField;
	
	@JsonProperty("ErrorCode")
	private String errorCode;
	
	@JsonProperty("ErrorDesc")
	private String errorDesc;
}
