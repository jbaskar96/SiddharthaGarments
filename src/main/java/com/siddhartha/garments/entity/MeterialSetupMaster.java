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
@Table(name="METERIAL_SETUP_MASTER")
@Entity
@Builder
public class MeterialSetupMaster {
	
	
	@EmbeddedId
	private MeterialSetupMasterId metId;
	
	@Column(name="TABLE_COLUMN_NAME")
	private String tableColumnName;
	
	@Column(name="DISPLAY_COLUMN")
	private String displayColumn;
	
	@Column(name="QUANTITY_TYPE")
	private String quantityType;
	
	@Column(name="CALC_TYPE")
	private String calcType;
	
	@Column(name="BOX_PIECES")
	private Integer boxPieces;
	
	@Column(name="PERCENTAGE_VALUE")
	private Integer percentageValue;
	
	@Column(name="FLAT_PIECES")
	private Integer flatPieces;
	
	@Column(name="DISPLAY_ORDER")
	private Integer displayOrder;
	
	@Column(name="ENTRY_DATE")
	private Date entryDate;
	
	@Column(name="STATUS")
	private String status;

}
