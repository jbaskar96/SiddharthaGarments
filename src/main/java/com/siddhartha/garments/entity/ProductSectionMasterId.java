package com.siddhartha.garments.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

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
@Embeddable
public class ProductSectionMasterId implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

	@Column(name="COMPANY_ID")
	private Integer companyId;

	@Column(name="PRODUCT_ID")
	private Integer productId;
	
	@Column(name="SECTION_ID")
	private Integer sectionId;

}
