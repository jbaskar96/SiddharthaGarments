/* 
*  Copyright (c) 2019. All right reserved
 * Created on 2021-08-04 ( Date ISO 2021-08-04 - Time 19:48:34 )
 * Generated by Telosys Tools Generator ( version 3.3.0 )
 */

/*
 * Created on 2021-08-04 ( 19:48:34 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.3.0
 */


package com.siddhartha.garments.entity;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;




/**
* Domain class for entity "LoginMaster"
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
@Table(name="LOGIN_MASTER")
public class LoginMaster implements Serializable {
 
private static final long serialVersionUID = 1L;
 
    //--- ENTITY PRIMARY KEY 
    @Id
    @Column(name="LOGIN_ID", nullable=false, length=50)
    private String     loginId ;

    //--- ENTITY DATA FIELDS 
    @Column(name="PASSWORD", length=100)
    private String     password ;

    @Column(name="USERTYPE", length=15)
    private String     usertype ;

    @Column(name="CREATED_BY", length=30)
    private String     createdBy ;

    @Column(name="STATUS", length=1)
    private String     status ;

    @Column(name="ENTRY_DATE", length=1)
    private Date   entryDate ;
   
}


