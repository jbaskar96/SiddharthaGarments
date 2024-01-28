/* 
*  Copyright (c) 2019. All right reserved
 * Created on 2021-08-13 ( Date ISO 2021-08-13 - Time 15:03:28 )
 * Generated by Telosys Tools Generator ( version 3.3.0 )
 */

/*
 * Created on 2021-08-13 ( 15:03:28 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.3.0
 */


package com.siddhartha.garments.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;




/**
* Domain class for entity "SessionDetails"
*
* @author Telosys Tools Generator
*
*/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@DynamicInsert
@DynamicUpdate
@Builder
@Table(name = "SESSION_TABLE")
public class SessionDetails {
 
 
	@EmbeddedId
	private SessionDetailsId sessionPk;
   
    //--- ENTITY DATA FIELDS 
    @Column(name="STATUS")
    private String    status ;

    @Column(name="TEMP_TOKENID")
    private String     tempTokenid ;

    @Column(name="ENTRY_DATE")
    private Date   entryDate; 
    
    @Column(name="SESSION_ID")
    private String   sessionId; ;

    @Column(name="USER_TYPE")
    private String   userType; ;

}



