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

@Setter@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="EXPENSIVE_DETAILS")
@Builder
public class ExpensiveDetails {
	
	
	@Id
	@Column(name="SERIAL_NO")
	private Long serialNo;
	
	@Column(name="ACCOUNT_TYPE")
	private String accountType;

	@Column(name="CATEGORY_TYPE")
	private String categoryType;

	@Column(name="AMOUNT")
	private Double amount;

	@Column(name="NOTES")
	private String notes;
	
	@Column(name="EXPENSIVE_DATE")
	private Date expeniveDate;

	@Column(name="ENTRY_DATE")
	private Date entryDate;

	@Column(name="STATUS")
	private String status;

	


}
