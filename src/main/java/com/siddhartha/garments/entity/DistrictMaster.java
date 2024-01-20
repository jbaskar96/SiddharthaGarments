package com.siddhartha.garments.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

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
@Entity
@Table(name="DISTRICT_MASTER")
@IdClass(DistrictMasterID.class)
public class DistrictMaster {
	
	
	@Id
	@Column(name="STATE_CODE")
	private Integer stateCode;
	
	@Id
	@Column(name="DISTRICT_CODE")
	private Integer districtCode;
	
	@Column(name="DISTRICT_NAME")
	private String districtName;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="ENTRY_DATE")
	private Date entryDate;

}
