/*
 * Created on 2021-08-13 ( 15:03:28 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.3.0
 */
package com.siddhartha.garments.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



/**
 * Composite primary key for entity "SessionDetails" ( stored in table "SESSION_DETAILS" )
 *
 * @author Telosys
 *
 */

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class SessionDetailsId implements Serializable {

    private static final long serialVersionUID = 1L;
    
    
    //--- ENTITY PRIMARY KEY 

    @Column(name="LOGIN_ID", nullable=false, length=100)
    private String     loginId ;


    @Column(name="TOKEN_ID", nullable=false, length=500)
    private String     tokenId ;

     
}
