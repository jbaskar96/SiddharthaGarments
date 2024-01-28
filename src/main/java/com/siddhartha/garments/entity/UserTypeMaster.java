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
@Table(name="USERTYPE_MASTER")
@Builder
public class UserTypeMaster {
	
	
	@Id
	@Column(name = "USERTYPE_ID")
	private Integer userTypeId;

	@Column(name = "USER_TYPE")
	private String userType;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "ENTRY_DATE")
	private Date entryDate;

}
