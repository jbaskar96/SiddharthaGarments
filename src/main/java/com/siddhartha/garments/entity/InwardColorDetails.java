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
@Table(name="INWARD_COLOR_DETAILS")
@Entity
public class InwardColorDetails {
	
	@EmbeddedId
	private InwardColorDetailsId colorDetailsId;
	
	@Column(name="COLOR")
	private Integer color;
	
	@Column(name="TOTAL_PIECES")
	private Integer totalPieces;
	
	@Column(name="ENTRY_DATE")
	private Date entryDate;
	
	@Column(name="STATUS")
	private String status;

}
