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
@Table(name="COMPANY_MASTER")
@Builder
public class CompanyMaster {
	
	@Id
	@Column(name="COMPANY_ID")
	private Integer companyId;
	
	@Column(name="COMPANY_NAME")
	private String companyName;
	
	@Column(name="STATE")
	private Integer state;
	
	@Column(name="DISTRICT")
	private Integer district;
	
	@Column(name="ADDRESS")
	private String address;
	
	@Column(name="GST_NUMBER")
	private String gstNumber;
	
	@Column(name="MOBILE_NO")
	private String mobileNo;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="ENTRY_DATE")
	private Date entryDate;
	
	@Column(name="REMARKS")
	private String remarks;
	
	@Column(name="CREATED_BY")
	private String createdBy;
	

}
