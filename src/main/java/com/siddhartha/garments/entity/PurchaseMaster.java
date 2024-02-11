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
@Table(name="PURCHASE_DETAILS")
@Builder
public class PurchaseMaster {
	
	@Id
	@Column(name="SERIAL_NO")
	private Long serialNo;

	
	@Column(name="CATEGORY_ID")
	private Long categoryId;
	
	@Column(name="BILL_REF_NO")
	private String billRefNo;
	
	@Column(name="SUPPLIER_NAME")
	private String supplierName;
	
	@Column(name="AMOUNT_BEFORE_TAX")
	private Double amountBeforetax;
	
	@Column(name="SGST")
	private Double sgst;
	
	@Column(name="CGST")
	private Double cgst;
	
	@Column(name="SGST_TAX")
	private Double sgstTax;
	
	@Column(name="CGST_TAX")
	private Double cgstTax;
	
	@Column(name="TOTAL_AMOUNT")
	private Double totalAmount;
	
	@Column(name="ENTRY_DATE")
	private Date entryDate;
	
	@Column(name="BILL_DATE")
	private Date billDate;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="PRODUCT")
	private String product;
	

}
