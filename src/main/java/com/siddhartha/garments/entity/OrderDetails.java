package com.siddhartha.garments.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name="ORDER_DETAILS")
@Entity
@Builder
public class OrderDetails {
	
	@Id
	@Column(name = "ORDER_ID")
	private String orderId;
	
	@Column(name = "COMPANY_ID")
	private Integer companyId;
	
	@Column(name = "PRODUCT_ID")
	private Integer productId;
	
	@Column(name = "STYLE_ID")
	private Integer styleId;
	
	@Column(name = "LOT_NUMBER")
	private String lotNumber;
	
	@Column(name = "INWARD_DATE")
	private Date inwardDate;
	
	@Column(name = "ENTRY_DATE")
	private Date entryDate;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "REMARKS")
	private String remarks;
	
	@Column(name = "CREATEDBY")
	private String createdBy;
	
}
