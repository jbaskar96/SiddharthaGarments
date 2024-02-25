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
@Table(name = "PRODUCT_SIZE_METAL_MASTER")
@Builder
public class ProductSizeMetalMaster {
	
	@EmbeddedId
	private ProductSizeMetalMasterId id;
	
	@Column(name="METAL_NAME")
	private String metalName;
	
	@Column(name="COLUMN_NAME")
	private String columnName;
	
	@Column(name="MESUREMENT_TYPE")
	private String mesurementType;
	
	@Column(name="MESUREMENT_VALUE")
	private Double mesurementValue;
	
	@Column(name="MESUREMENT_PIECES")
	private Integer mesurementPieces;
	
	@Column(name="DISPLAY_ORDER")
	private Integer displayOrder;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="ENTRY_DATE")
	private Date entryDate;
	

}
