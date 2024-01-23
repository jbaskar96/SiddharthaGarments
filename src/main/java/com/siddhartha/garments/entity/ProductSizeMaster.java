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
@Table(name="PRODUCT_SIZE_MASTER")
public class ProductSizeMaster {

	
	@Id
	@Column(name="PRODUCTSIZE_ID")
	private Integer productSizeId;
	
	@Column(name="PRODUCT_SIZE")
	private Integer productSize;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="ENTRY_DATE")
	private Date entryDate;
}
