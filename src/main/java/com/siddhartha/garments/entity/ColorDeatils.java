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
@Table(name = "COLOR_DETAILS")
@Builder
public class ColorDeatils {
	
	@EmbeddedId
	private ColorDeatilsId colId;
	
	@Column(name="PIECES_COUNT")
	private Integer piecesCount;
	
	@Column(name="COLOR_CODE")
	private Integer colorCode;
	
	@Column(name="NO_OF_BUNDLE")
	private Integer noOfBundle;
	
	@Column(name="TOTAL_DOZEN")
	private Integer totalDozen;
	
	@Column(name="REQUIRED_FOLDING_WEIGHT")
	private Double requiredFoldingWeight;
	
	@Column(name="RECEVIED_FOLDING_WEIGHT")
	private Double receivedFoldingWeight;
	
	@Column(name="REQUIRED_ELASTIC")
	private Integer requiredElastic;
	
	@Column(name="RECEIVED_ELASTIC")
	private Integer receivedElastic;
	
	@Column(name="STATUS")
	private String status;

	@Column(name="REMARKS")
	private String remarks;
	
	@Column(name="ENTRY_DATE")
	private Date entryDate;

}
