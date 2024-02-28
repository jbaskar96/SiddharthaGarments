package com.siddhartha.garments.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.siddhartha.garments.entity.OrderDetails;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, String> {

	List<OrderDetails> findByStatusIgnoreCase(String status, Sort sort);
	
	@Query(nativeQuery=true,value ="select * from METAL_CALC_DEATILS where oreder_id=?1")
	List<Map<String,Object>> getMetalDetails(String orderId);

}
