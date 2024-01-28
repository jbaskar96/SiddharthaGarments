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
@Table(name = "METERIAL_DETAILS")
@Builder
public class Meterialdetails {
	
	@EmbeddedId
	private MeterialdetailsId metId;
	
	@Column(name="METAL_TYPE")
	private String metalType;
	
	@Column(name="LABLE")
	private Integer lable;
	
	@Column(name="SIZE_STICKER")
	private Integer sizeSticker;
	
	@Column(name="INNER_CARD")
	private Integer innerCard;
	
	@Column(name="POLICY_BAG")
	private Integer policyBag;
	
	@Column(name="SINGLE_BOX")
	private Integer singleBox;
	
	@Column(name="MASTER_BOX")
	private Integer masterBox;
	
	@Column(name="MRP_STICKER")
	private Integer mrpSticker;
	
	@Column(name="BODY_STICKER")
	private Integer bodySticker;
	
	@Column(name="HOLOGRAM_STICKER")
	private Integer hologramSticker;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="ENTRY_DATE")
	private Date entryDate;
	
	@Column(name="REMARKS")
	private String remarks;
	

}
