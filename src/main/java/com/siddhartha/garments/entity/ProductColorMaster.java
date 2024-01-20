package com.siddhartha.garments.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Builder
@Table(name="PRODUCT_COLOR_MASTER")
public class ProductColorMaster {
	
	@Id
	@Column(name="COLOR_ID")
	private Integer colorId;
	
	@Column(name="COLOR_NAME")
	private String colorName;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="ENTRY_DATE")
	private Date entryDate;

}
