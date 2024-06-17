package com.siddhartha.garments.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyMeterialMasterID implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer meterialId;
	
	private Integer companyId;
	
	private Integer productId;
	
	private Integer colorId;
	
	private Integer sizeId;

}
