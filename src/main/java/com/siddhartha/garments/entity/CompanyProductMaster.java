package com.siddhartha.garments.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="COMPANY_PRODUCT_MASTER")
@Entity
@Builder
public class CompanyProductMaster {
	
	
	@EmbeddedId
	private CompanyProductMasterId id;
	
	@Column(name="PRODUCT_NAME")
	private String productName;

	@Column(name="REMARKS")
	private String remarks;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="ENTRY_DATE")
	private Date entryDate;
	
	@Column(name="CREATED_BY")
	private String createdBy;
	
	
	

}
