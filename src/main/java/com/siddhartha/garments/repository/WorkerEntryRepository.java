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

}
