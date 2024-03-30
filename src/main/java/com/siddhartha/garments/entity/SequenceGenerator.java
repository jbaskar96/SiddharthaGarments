package com.siddhartha.garments.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name ="SEQUENCE_GENERATOR")
public class SequenceGenerator {

		@Id
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
	    @Column(name="SEQUENCE_NO", nullable=false)
	    private Long      sequenceNo;

}
