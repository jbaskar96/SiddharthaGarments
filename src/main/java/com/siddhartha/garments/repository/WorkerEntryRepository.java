package com.siddhartha.garments.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.siddhartha.garments.entity.WorkerEntryDetails;

@Repository
public interface WorkerEntryRepository  extends JpaRepository<WorkerEntryDetails, Long>{

	List<WorkerEntryDetails> findByOrderId(String orderId);

	@Query(nativeQuery=true,value="select * from WORKER_ENTRY_DETAILS")
	Object[][] getReportDeatils();

	List<WorkerEntryDetails> findByOrderIdAndChallanIdAndColorId(String orderId, String challanId, String colorId);

	@Query(value ="SELECT COUNT(*) FROM worker_entry_details WHERE order_id =?1 AND challan_id=?2 AND color_id=?3 AND section_id=?4 AND operator_id=?5 AND DATE(entry_date)=DATE(CURRENT_DATE)",nativeQuery=true)
	Long checkDuplicateEntry(String orderId,String challanId,String colorId,String sectionId,String operatorId);

	@Query(nativeQuery=true,value="SELECT(SELECT company_name FROM company_master cm WHERE cm.company_id =we.company_id) AS company_name,(SELECT product_name FROM product_master pm WHERE pm.company_id =we.company_id AND pm.product_id =we.product_id) AS product_name,order_id,lot_number,challan_no, color_name,total_pieces,employee_worked_pieces,total_amount,(SELECT udm.first_name FROM userdetails_master udm WHERE udm.user_id=we.operator_id) AS operator_name,(SELECT psm.section_name FROM product_section_master psm WHERE psm.company_id =we.company_id AND psm.product_id =we.product_id AND psm.section_id =we.section_id) AS section_name FROM worker_entry_details we WHERE date(we.entry_date) BETWEEN ?1 AND ?2")
	List<Map<String, Object>> getWorkerReport(String start_date, String end_date);

	@Query(nativeQuery=true,value="SELECT(SELECT company_name FROM company_master cm WHERE cm.company_id =we.company_id) AS company_name,(SELECT product_name FROM product_master pm WHERE pm.company_id =we.company_id AND pm.product_id =we.product_id) AS product_name,order_id,lot_number,challan_no, color_name,total_pieces,employee_worked_pieces,total_amount,(SELECT udm.first_name FROM userdetails_master udm WHERE udm.user_id=we.operator_id) AS operator_name,(SELECT psm.section_name FROM product_section_master psm WHERE psm.company_id =we.company_id AND psm.product_id =we.product_id AND psm.section_id =we.section_id) AS section_name FROM worker_entry_details we WHERE date(we.entry_date) BETWEEN ?1 AND ?2 and we.operator_id=?3")
	List<Map<String, Object>> getWorkerReport(String start_date, String end_date,String opratorId);

}
