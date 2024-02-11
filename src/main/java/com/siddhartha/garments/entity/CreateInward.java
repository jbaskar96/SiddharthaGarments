package com.siddhartha.garments.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="INWARD_DETAILS")
@Entity
public class CreateInward {
	
	@Id
	@Column(name = "INWARD_ID")
	private String inwardId;
	
	@Column(name = "LOT_NUMBER")
	private String lotNumber;
	
	@Column(name = "COMPANY_NAME")
	private String companyName;
	
	@Column(name = "STATE")
	private String state;
	
	@Column(name = "DISTRICT")
	private String district;
	
	@Column(name = "ADDRESS")
	private String address;
	
	@Column(name = "BRAND")
	private String brand;
	
	@Column(name = "CATEGORY")
	private String category;
	
	@Column(name = "SIZE")
	private Integer size;
	
	@Column(name = "FOLDINGYN")
	private String foldingYn;
	
	@Column(name = "ENTRY_DATE")
	private Date entryDate;
	
	@Column(name = "STATUS")
	private String status;

}
