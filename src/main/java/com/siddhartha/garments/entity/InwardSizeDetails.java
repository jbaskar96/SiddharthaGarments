package com.siddhartha.garments.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="INWARD_SIZE_DETAILS")
@Entity
public class InwardSizeDetails {
	
	@EmbeddedId
	private InwardSizeDetailsId sizeDetailsId;
	
	@Column(name="CHALLAN_NUMBER")
	private Integer challanNumber;
	
	@Column(name="CHALLAN_DATE")
	private  Date challanDate;
	
	@Column(name="SIZE")
	private Integer size;
	
	@Column(name="TOTAL_PIECES")
	private  Integer totalPieces;
	
	@Column(name="ENTRY_DATE")
	private  Date entryDate;
	
	@Column(name="STATUS")
	private  String status;

}
