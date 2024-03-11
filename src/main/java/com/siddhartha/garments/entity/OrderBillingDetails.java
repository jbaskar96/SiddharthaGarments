package com.siddhartha.garments.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ORDER_BILLING_DETAILS")
@Builder
public class OrderBillingDetails {
	
	@Id
	@Column(name = "ORDER_ID")
	private String orderId;
	
	@Column(name = "TOTAL_PIECES")
	private Integer totalPieces;
	
	@Column(name = "DELIVERY_TYPE")
	private String deliveryType;
	
	@Column(name = "NOOFBOX_DOZENS")
	private Integer noOfboxDozens;
	
	@Column(name = "NOOF_PIECES")
	private Integer noOfPieces;
	
	@Column(name = "TOTAL_BOX")
	private Integer totalBox;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "REMARKS")
	private String remarks;
	
	@Column(name = "ENTRY_DATE")
	private Date entryDate;
	
	@Column(name = "UPDATEDBY")
	private String updatedBy;
}
