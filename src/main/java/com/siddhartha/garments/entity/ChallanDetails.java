package com.siddhartha.garments.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Entity
@Table(name = "CHALLAN_DETAILS")
@Builder
public class ChallanDetails {/**
	 * 
	 */
	
	@EmbeddedId
	private ChallanDetailsId chaId;
	
	@Column(name="CHALLAN_NO")
	private String challanNo;
	
	@Column(name="CHALLAN_DATE")
	private Date challanDate;
	
	@Column(name="QUALITY")
	private String quality;
	
	@Column(name="ENTRY_DATE")
	private Date entryDate;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="SIZE")
	private Integer size;
	
	@Column(name="PIECEYN")
	private String pieceyn;
	
	@Column(name="TOTAL_PIECE")
	private Integer totalPiece;
	
	
	
}
