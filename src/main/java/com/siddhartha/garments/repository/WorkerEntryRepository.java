package com.siddhartha.garments.repository;

import java.util.List;

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

}
