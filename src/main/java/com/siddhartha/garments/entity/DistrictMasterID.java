package com.siddhartha.garments.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
@AllArgsConstructor
@Builder
public class DistrictMasterID implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1787488342067734839L;

	@Column(name = "STATE_CODE")
	private Integer stateCode;
	
	@Column(name = "DISTRICT_CODE")
	private Integer districtCode;

}
