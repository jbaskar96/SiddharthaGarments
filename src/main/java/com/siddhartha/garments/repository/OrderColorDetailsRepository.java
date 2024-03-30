package com.siddhartha.garments.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siddhartha.garments.entity.OrderColorDetails;
import com.siddhartha.garments.entity.OrderColorDetailsId;

@Repository
public interface OrderColorDetailsRepository  extends JpaRepository<OrderColorDetails, OrderColorDetailsId>{

	List<OrderColorDetails> findByIdOrderIdAndIdChallanId(String orderId,String challanId);

}
