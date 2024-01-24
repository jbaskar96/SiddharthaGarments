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
@Table(name="USERDETAILS_MASTER")
public class UserDetailsMaster {

	
	@Id
	@Column(name="USER_ID")
	private String userId;
	
	@Column(name="LOGIN_ID")
	private String loginId;
	
	@Column(name="FIRST_NAME")
	private String firstName;
	
	@Column(name="LAST_NAME")
	private String lastName;
	
	@Column(name="MOBILE_NO")
	private String mobileNo;
	
	@Column(name="AADHAR_NO")
	private String aadharNo;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="DATE_OF_BIRTH")
	private Date dateOfBirth;
	
	@Column(name="CREATED_BY")
	private String createdBy;
	
	@Column(name="ENTRY_DATE")
	private Date entryDate;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="USER_TYPE")
	private Integer userType;
	
	@Column(name="STATE_CODE")
	private Integer stateCode;
	
	@Column(name="DISTRICT_CODE")
	private Integer districtCode;
	
	@Column(name="CITY")
	private String city;
	
	@Column(name="ADDRESS")
	private String address;
	
}
