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
@Table(name="PRODUCT_STYLE_MASTER")
@Entity
public class ProductStyleMaster {
	
	@EmbeddedId
	private ProductStyleMasterId id;
	
	@Column(name="STYLE_NAME")
	private String styleName;
	
	@Column(name="REMARKS")
	private String remarks;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="ENTRY_DATE")
	private Date entryDate;
	
	@Column(name="CREATED_BY")
	private String createdBy;

}
