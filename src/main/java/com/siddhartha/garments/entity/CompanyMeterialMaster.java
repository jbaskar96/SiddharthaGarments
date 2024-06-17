package com.siddhartha.garments.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
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
@Table(name = "COMPANY_METERIAL_MASTER")
@Builder
@IdClass(CompanyMeterialMasterID.class)
public class CompanyMeterialMaster {
	
	
	@Id
	@Column(name = "COMPANY_ID")
	private Integer companyId;
	
	@Id
	@Column(name = "PRODUCT_ID")
	private Integer productId;

	@Id
	@Column(name = "SIZE_ID")
	private Integer sizeId;

	@Id
	@Column(name = "COLOR_ID")
	private Integer colorId;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
	@Column(name = "METERAIL_ID",updatable = false,nullable = false)
	private Integer meterialId;

	
	@Column(name = "MEASUREMENT_NAME")
	private String measurementName;
	
	@Column(name = "MEASUREMENT_DISPLAY_NAME")
	private String measurementDisplayName;
	
	@Column(name = "MEASUREMENT_VALUE")
	private Double measurementValue;
	
	@Column(name = "MEASUREMENT_PIECES")
	private Integer measurementPieces;
	
	@Column(name = "MEASUREMENT_TYPE")
	private String measurementType;
	
	@Column(name = "MEASUREMENT_DISPLAY_ORDER")
	private Integer measurementDisplayOrder;
	
	@Column(name = "DB_COLUMN_NAME")
	private String dbColumnName;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name ="ENTRY_DATE")
	private Date entryDate;

}
