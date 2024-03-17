package com.siddhartha.garments.repository;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.siddhartha.garments.entity.OrderDetails;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, String> {

	List<OrderDetails> findByStatusIgnoreCase(String status, Sort sort);
	
	@Query(nativeQuery=true,value ="select * from METAL_CALC_DEATILS where order_id=?1")
	List<Map<String,Object>> getMetalDetails(String orderId);
	
	@Transactional
	@Modifying
	@Query(nativeQuery=true,value ="delete from METAL_CALC_DEATILS where order_id=?1 and color_id is null")
	Integer deleteSizeMetalByOrderId(String orderId);
	

	@Transactional
	@Modifying
	@Query(nativeQuery=true,value ="delete from METAL_CALC_DEATILS where order_id=?1 and color_id is not null")
	Integer deleteColorMetalByOrderId(String orderId);

	@Query(nativeQuery=true,value ="select ?1 from METAL_CALC_DEATILS where ORDER_ID =?2 and CHALLAN_ID=?3 and TYPE_NAME=?4")
	Object[] getOrderSizeRequired(String params, String orderId, String challanId, String type);

}
