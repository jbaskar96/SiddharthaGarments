package com.siddhartha.garments.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DistrictMasterID implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1787488342067734839L;

	private Integer stateCode;
	
	private Integer districtCode;

}
