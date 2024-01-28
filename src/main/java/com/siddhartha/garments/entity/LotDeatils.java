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
@Entity
@Table(name = "LOT_DETAILS")
@Builder
public class LotDeatils {

	
	@Id
	@Column(name = "ORDER_ID")
	private String orderId;
	
	@Column(name = "LOT_NO")
	private String lotNo;
	
	@Column(name = "COMPANY_NAME")
	private String companyName;
	
	@Column(name = "STATE_CODE")
	private Integer stateCode;
	
	@Column(name = "DISTRICT_CODE")
	private Integer districtCode;
	
	@Column(name = "CITY")
	private String city;
	
	@Column(name = "ADDRESS")
	private String address;
	
	@Column(name = "GST_NO")
	private String gstNo;
	
	@Column(name = "PHONE_NO")
	private String phoneNo;
	
	@Column(name = "INWARD_DATE")
	private Date inwardDate;
	
	@Column(name = "PRODUCTION_DATE")
	private Date productionDate;
	
	@Column(name = "DELIVERY_DATE")
	private Date deliveryDate;
	
	@Column(name = "PRODUCTION_REMARKS")
	private String productionRemarks;
	
	@Column(name = "DELIVERY_REMARKS")
	private String deliveryRemarks;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "ENTRY_DATE")
	private Date entryDate;
	
	@Column(name = "PRODUCT_CODE")
	private Integer productCode;
	
	@Column(name = "UPDATEDBY")
	private String updatedBy;
	
	@Column(name = "UPDATED_DATE")
	private Date updatedDate;
	
}
