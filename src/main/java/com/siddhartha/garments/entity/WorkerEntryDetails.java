package com.siddhartha.garments.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "WORKER_ENTRY_DETAILS")
@Builder
public class WorkerEntryDetails {
	
	@Id
	@Column(name="SERIAL_NO")
	private Long serialNo;
	
	@Column(name="OPERATOR_ID")
	private String operatorId;
	
	@Column(name="OPERATOR_NAME")
	private String operatorName;
	
	@Column(name="SECTION_ID")
	private Integer sectionId;
	
	@Column(name="SECTION_NAME")
	private String sectionName;
	
	@Column(name="ORDER_ID")
	private String orderId;
	
	@Column(name="LOT_NUMBER")
	private String lotNumber;
	
	@Column(name="CHALLAN_ID")
	private String challanId;
	
	@Column(name="CHALLAN_NO")
	private String challanNo;
	
	@Column(name="COLOR_ID")
	private String colorId;
	
	@Column(name="COLOR_NAME")
	private String colorName;
	
	@Column(name="TOTAL_PIECES")
	private Integer totalPieces;
	
	@Column(name="WORKED_PIECES")
	private Integer workedPieces;
	
	@Column(name="DAMAGED_PIECES")
	private Integer damagedPieces;
	
	@Column(name="GOOD_PIECES")
	private Integer goodPieces;
	
	@Column(name="UPDATED_BY")
	private String updatedBy;
	
	@Column(name="ENTRY_DATE")
	private Date entryDate;
	
	@Column(name="STATUS")
	private String status;

}
