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
@Builder
@Entity
@Table(name="PRODUCT_SECTION_MASTER")
public class ProductSectionMaster {
	
	
	@EmbeddedId
	private ProductSectionMasterId id;

	@Column(name="NOOF_PIECES")
	private Integer noOfPieces;
	
	@Column(name="PIECE_AMOUNT")
	private Double piecesAmount;
	
	@Column(name="SECTION_NAME")
	private String sectionName;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="ENTRY_DATE")
	private Date entryDate;
}
