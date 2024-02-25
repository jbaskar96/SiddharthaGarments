package com.siddhartha.garments.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Builder
public class ProductSizeMetalMasterId implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="COMPANY_ID")
	private Integer companyId;
	
	@Column(name="PRODUCT_ID")
	private Integer productId;
	
	@Column(name="SIZE_ID")
	private Integer sizeId;
	
	@Column(name="METAL_ID")
	private Integer metalId;


}
