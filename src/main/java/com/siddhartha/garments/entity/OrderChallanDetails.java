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
@Builder
@Entity
@Table(name="ORDER_CHALLAN_DETAILS")
public class OrderChallanDetails {
	
	@EmbeddedId
	private OrderChallanDetailsId id;
	
	@Column(name="CHALLAN_NUMBER")
	private String challanNumber;
	
	@Column(name="CHALLAN_DATE")
	private Date challanDate;
	
	@Column(name="SIZE_ID")
	private Integer sizeId;
	
	@Column(name="SIZE")
	private Integer size;
	
	@Column(name="TOTAL_PIECES")
	private Integer totalPieces;
	
	@Column(name="ENTRY_DATE")
	private Date entryDate;
	
}
