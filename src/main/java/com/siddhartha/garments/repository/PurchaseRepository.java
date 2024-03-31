package com.siddhartha.garments.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.siddhartha.garments.entity.PurchaseMaster;

@Repository
public interface PurchaseRepository extends JpaRepository<PurchaseMaster, Long> {

	@Query(nativeQuery=true,value="select pd.supplier_name,pd.bill_date,pd.amount_before_tax,pd.total_amount,pd.bill_ref_no,pd.cgst_tax,pd.sgst_tax, case when pd.category_id ='1' then 'GST' ELSE 'NON_GST' end AS bill_type,pd.entry_date from purchase_details pd WHERE date(pd.entry_date) BETWEEN ?1 AND ?2 ")
	List<Map<String,Object>> getPurchaseReport(String start_date, String end_date);

	@Query(nativeQuery=true,value="select pd.supplier_name,pd.bill_date,pd.amount_before_tax,pd.total_amount,pd.bill_ref_no,pd.cgst_tax,pd.sgst_tax, case when pd.category_id ='1' then 'GST' ELSE 'NON_GST' end AS bill_type,pd.entry_date from purchase_details pd WHERE date(pd.entry_date) BETWEEN ?1 AND ?2 and pd.category_id=?3 ")
	List<Map<String,Object>> getPurchaseReport(String start_date, String end_date, String categoryType);

}
