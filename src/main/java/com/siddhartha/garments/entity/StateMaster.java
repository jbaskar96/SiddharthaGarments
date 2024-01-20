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
@Builder
@Entity
@Table(name="STATE_MASTER")
public class StateMaster {
	
	
	@Id
	@Column(name="STATE_CODE")
	private String stateCode;
	
	@Column(name="STATE_NAME")
	private String stateName;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="ENTRY_DATE")
	private Date entryDate;

}
