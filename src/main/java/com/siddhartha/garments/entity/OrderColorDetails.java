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
@Table(name ="ORDER_SIZE_COLOR_DETAILS")
@Builder
public class OrderColorDetails {
	
	@EmbeddedId
	private OrderColorDetailsId id;
	
	@Column(name ="COLOR_CODE")
	private Integer colorCode;
	
	@Column(name ="COLOR_NAME")
	private String colorName;
	
	@Column(name ="TOTAL_PIECES")
	private Integer totalPieces;
	
	@Column(name ="ENTRY_DATE")
	private Date entryDate;
	
}
