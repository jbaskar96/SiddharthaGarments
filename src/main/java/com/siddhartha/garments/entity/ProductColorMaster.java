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
@Table(name = "PRODUCT_COLOR_MASTER")
@Builder
public class ProductColorMaster {
	
	@EmbeddedId
	private ProductColorMasterId id;
	
	@Column(name="COLOUR_NAME")
	private String colourName;
	
	@Column(name="STATUS")
	private String status;

	@Column(name="ENTRY_DATE")
	private Date entryDate;

	
}
